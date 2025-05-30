### Project Setup Guide

## PLangRec Model
The PLangRec model has its own GitHub repository with installation instructions. Follow the steps outlined in the official documentation: [PLangRec Web API Installation](https://github.com/ComputationalReflection/PLangRec/blob/main/web-api.md)
This was not included in this repository as it is a large amount of files to be transferred. The server is configured to run on port 8000. Make sure to install all dependencies as described in their ReadME file. Be sure to place this GitHub directory directly into the root of this one.

## RESICO Model
To set up the RESCIO model you can install required dependencies using pip from the requirements.txt file. You might need to install additional libraries such as joblib, but the versions for these don't matter. The server is ocnfigured to run on port 5000.

## ANTLR Setup
To properly generate the parser you need to run the command "mvn clean package" in the antrl directory. This will generate the necessary JAR files and target files for the parser. 

## Running Models and Extension
Models will only run in seperate environments. You need to make sure you have two seperate environments (use conda for example). PLangRec requires you to run the main.py file within their web-api directory, whilst RESICO requires you to run api.py in the RESICO directory.
Finally, to deploy the extension, you need to open Google Chrome in Developer Mode. Then, load the unpacked extension and make sure that all models are running properly. Once all components are correctly installed and configured, the setup should work.
