// Makes use of the HTML tags to identify if there is java-code present. (Does not ensure all code is in Java.)
function isJavaCode(codeElement) {
  return codeElement.classList.contains("language-java");
}

function hasImports(code) {
  return code.includes("import");
}

// Get request the PLangRec model to confirm the code is Java.
// https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch
async function isConfirmedJava(code) {
  try {
    const encodedCode = encodeURIComponent(code);
    url = `http://localhost:8000/BRNN/predict?source_code=${encodedCode}`;
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    const json = await response.json();
    console.log("PLangRec response:", json.Java);
    // console.log(json);
    return json.Java > 75.0;
  } catch (error) {
    console.error(error.message);
  }
}

// Function that will write the code snippets to a JSON file, this is to easily check if the extraction is correct.
// Script is running in the browser so require won't work. Work with blobs instead: https://developer.mozilla.org/en-US/docs/Web/API/Blob
function downloadSnippets(snippets) {
  const cleanedSnippets = snippets.map((snippet) => ({
    ...snippet,
    code: snippet.code
      .replace(/\/\/.*/g, "")
      .replace(/\n/g, " ")
      .replace(/\s{2,}/g, " ")
      .trim(),
  }));

  const blob = new Blob([JSON.stringify(cleanedSnippets, null, 2)], {
    type: "application/json",
  });

  // Create download link for blob
  const url = URL.createObjectURL(blob);
  const a = document.createElement("a");
  a.href = url;
  a.download = "snippets.json";
  a.click();
  URL.revokeObjectURL(url);
}

// Api POST request that will return the response as a json file, similar to PLangRec get request
async function fetchImportStatementFromModel(code) {
  // Clean up code like in downloadCodeSnippets
  const cleanedCode = code
    .replace(/\/\/.*/g, "")
    .replace(/\/\*[\s\S]*?\*\//g, "")
    .replace(/\n/g, " ")
    .replace(/\s{2,}/g, " ")
    .trim();

  try {
    const response = await fetch("http://localhost:5000/predict", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ code: cleanedCode }),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error requesting import statement:", error);
    return null;
  }
}

// Adding the import statement into the DOM
async function addImportStatementsToDOM(snippet, importStatements) {
  const isQuestion = snippet.isQuestion || false;
  const elementId = isQuestion ? snippet.questionId : snippet.answerId;
  const selector = isQuestion
    ? `.question[data-questionid="${elementId}"]`
    : `.answer[data-answerid="${elementId}"]`;
  const element = document.querySelector(selector);
  if (!element) {
    console.error(`Element not found with selector: ${selector}`);
    return;
  }

  // First fine the <code class="language-java"> element
  const codeElement = element.querySelector("pre code.language-java");
  if (!codeElement) {
    console.error("No <code> element found to insert imports into.");
    return;
  }

  // Then build the string of the import statement lines.
  let importString = "";
  if (importStatements.predictions && importStatements.predictions.length > 0) {
    importString =
      importStatements.predictions
        .map((importPath) => `import ${importPath};`)
        // Add empty lines between the import statements and the code snippet.
        .join("\n") + "\n\n";
  } else {
    println("Nothing to import here.");
  }

  const currentHTML = codeElement.innerHTML;
  const importHTML = `<span class="token keyword">${importString.replace(
    /\n/g,
    "<br>"
  )}</span>`;

  // Add the elements to the beginning/stard of precode.
  codeElement.innerHTML = importHTML + currentHTML;
}

// Function that will return the code in Stack Overlow answers that are 1) in the language Java, 2) at least 2 lines long and 3) does not have import statements.
async function collectAndVerifySnippets() {
  const answerBlocks = document.querySelectorAll(".answer");
  const snippets = [];

  // Process question first
  const questionElement = document.querySelector(".question");
  if (questionElement) {
    const questionCodeElements = questionElement.querySelectorAll(
      "pre code.language-java"
    );
    for (const codeElement of questionCodeElements) {
      const codeText = codeElement.textContent.trim();
      if (await isValidJavaSnippet(codeText)) {
        snippets.push({
          code: codeText,
          questionId: questionElement.dataset.questionid,
          isQuestion: true,
        });
      }
    }
  }

  // Process answers
  for (const answerBlock of answerBlocks) {
    const codeElements = answerBlock.querySelectorAll("pre code.language-java");
    const answerId = answerBlock.dataset.answerid;

    for (const codeElement of codeElements) {
      const codeText = codeElement.textContent.trim();
      if (await isValidJavaSnippet(codeText)) {
        snippets.push({
          code: codeText,
          answerId: answerId,
        });
      }
    }
  }

  return snippets;
}

// Helper function to validate Java snippets
async function isValidJavaSnippet(codeText) {
  const lines = codeText.split("\n");
  return (
    lines.length >= 1 &&
    !hasImports(codeText) &&
    (await isConfirmedJava(codeText))
  );
}

// Function that will send requests to resico and process imports.
async function fetchAndProcessImports(snippets) {
  // This for loop will request the RESICO model for each answer block.
  for (const snippet of snippets) {
    try {
      const importStatements = await fetchImportStatementFromModel(
        snippet.code
      );
      if (importStatements) {
        addImportStatementsToDOM(snippet, importStatements);
      }
    } catch (error) {
      console.error("Error requesting import", error);
    }
  }
}

// Main function
async function collectCodeSnippets() {
  const snippets = await collectAndVerifySnippets();
  console.log("These are the collected snippets", snippets);
  //downloadSnippets(snippets);
  fetchAndProcessImports(snippets).catch(console.error);
}

// MutationObserver API listens to changes made in the DOM and executes a function when certain elements change.
// In this case we wait for answerblocks to appear in the DOM before executing the function collectCodeSnippets.
const observer = new MutationObserver((mutations, obs) => {
  const answerblocks = document.querySelectorAll(".answer");
  if (answerblocks.length > 0) {
    collectCodeSnippets();
    obs.disconnect();
  }
});

// Attach observer to DOM.
observer.observe(document.body, {
  childList: true,
  subtree: true,
});
