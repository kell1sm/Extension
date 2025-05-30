// Generated from JavaSnippet.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class JavaSnippetLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, LT=9, 
		GT=10, COMMA=11, IDENTIFIER=12, STRING_LITERAL=13, NUMBER_LITERAL=14, 
		COMMENT=15, LINE_COMMENT=16, LBRACE=17, RBRACE=18, ARROW=19, COLON=20, 
		SLASH=21, SEMI=22, OTHER=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "LT", 
			"GT", "COMMA", "IDENTIFIER", "STRING_LITERAL", "NUMBER_LITERAL", "COMMENT", 
			"LINE_COMMENT", "LBRACE", "RBRACE", "ARROW", "COLON", "SLASH", "SEMI", 
			"OTHER", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'['", "']'", "'.'", "'new'", "'('", "')'", "'+'", "'<'", 
			"'>'", "','", null, null, null, null, null, "'{'", "'}'", "'->'", "':'", 
			"'/'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "LT", "GT", "COMMA", 
			"IDENTIFIER", "STRING_LITERAL", "NUMBER_LITERAL", "COMMENT", "LINE_COMMENT", 
			"LBRACE", "RBRACE", "ARROW", "COLON", "SLASH", "SEMI", "OTHER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JavaSnippetLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JavaSnippet.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0018\u00a8\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0005\u000bL\b\u000b\n\u000b\f\u000bO\t\u000b\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0005\fU\b\f\n\f\f\fX\t\f\u0001\f\u0001\f\u0001\r\u0003\r]\b"+
		"\r\u0001\r\u0004\r`\b\r\u000b\r\f\ra\u0001\r\u0001\r\u0004\rf\b\r\u000b"+
		"\r\f\rg\u0003\rj\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005"+
		"\u000ep\b\u000e\n\u000e\f\u000es\t\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0005\u000f~\b\u000f\n\u000f\f\u000f\u0081\t\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0017\u0004\u0017\u00a3\b\u0017\u000b\u0017\f\u0017\u00a4\u0001"+
		"\u0017\u0001\u0017\u0001q\u0000\u0018\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016-\u0017/\u0018\u0001\u0000\u0006\u0003\u0000"+
		"AZ__az\u0004\u000009AZ__az\u0002\u0000\"\"\\\\\u0001\u000009\u0002\u0000"+
		"\n\n\r\r\u0003\u0000\t\n\r\r  \u00b1\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f"+
		"\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000"+
		"\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000"+
		"\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000"+
		"-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00011\u0001"+
		"\u0000\u0000\u0000\u00033\u0001\u0000\u0000\u0000\u00055\u0001\u0000\u0000"+
		"\u0000\u00077\u0001\u0000\u0000\u0000\t9\u0001\u0000\u0000\u0000\u000b"+
		"=\u0001\u0000\u0000\u0000\r?\u0001\u0000\u0000\u0000\u000fA\u0001\u0000"+
		"\u0000\u0000\u0011C\u0001\u0000\u0000\u0000\u0013E\u0001\u0000\u0000\u0000"+
		"\u0015G\u0001\u0000\u0000\u0000\u0017I\u0001\u0000\u0000\u0000\u0019P"+
		"\u0001\u0000\u0000\u0000\u001b\\\u0001\u0000\u0000\u0000\u001dk\u0001"+
		"\u0000\u0000\u0000\u001fy\u0001\u0000\u0000\u0000!\u0084\u0001\u0000\u0000"+
		"\u0000#\u0088\u0001\u0000\u0000\u0000%\u008c\u0001\u0000\u0000\u0000\'"+
		"\u0091\u0001\u0000\u0000\u0000)\u0095\u0001\u0000\u0000\u0000+\u0099\u0001"+
		"\u0000\u0000\u0000-\u009d\u0001\u0000\u0000\u0000/\u00a2\u0001\u0000\u0000"+
		"\u000012\u0005=\u0000\u00002\u0002\u0001\u0000\u0000\u000034\u0005[\u0000"+
		"\u00004\u0004\u0001\u0000\u0000\u000056\u0005]\u0000\u00006\u0006\u0001"+
		"\u0000\u0000\u000078\u0005.\u0000\u00008\b\u0001\u0000\u0000\u00009:\u0005"+
		"n\u0000\u0000:;\u0005e\u0000\u0000;<\u0005w\u0000\u0000<\n\u0001\u0000"+
		"\u0000\u0000=>\u0005(\u0000\u0000>\f\u0001\u0000\u0000\u0000?@\u0005)"+
		"\u0000\u0000@\u000e\u0001\u0000\u0000\u0000AB\u0005+\u0000\u0000B\u0010"+
		"\u0001\u0000\u0000\u0000CD\u0005<\u0000\u0000D\u0012\u0001\u0000\u0000"+
		"\u0000EF\u0005>\u0000\u0000F\u0014\u0001\u0000\u0000\u0000GH\u0005,\u0000"+
		"\u0000H\u0016\u0001\u0000\u0000\u0000IM\u0007\u0000\u0000\u0000JL\u0007"+
		"\u0001\u0000\u0000KJ\u0001\u0000\u0000\u0000LO\u0001\u0000\u0000\u0000"+
		"MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000N\u0018\u0001\u0000"+
		"\u0000\u0000OM\u0001\u0000\u0000\u0000PV\u0005\"\u0000\u0000QU\b\u0002"+
		"\u0000\u0000RS\u0005\\\u0000\u0000SU\t\u0000\u0000\u0000TQ\u0001\u0000"+
		"\u0000\u0000TR\u0001\u0000\u0000\u0000UX\u0001\u0000\u0000\u0000VT\u0001"+
		"\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WY\u0001\u0000\u0000\u0000"+
		"XV\u0001\u0000\u0000\u0000YZ\u0005\"\u0000\u0000Z\u001a\u0001\u0000\u0000"+
		"\u0000[]\u0005-\u0000\u0000\\[\u0001\u0000\u0000\u0000\\]\u0001\u0000"+
		"\u0000\u0000]_\u0001\u0000\u0000\u0000^`\u0007\u0003\u0000\u0000_^\u0001"+
		"\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000"+
		"ab\u0001\u0000\u0000\u0000bi\u0001\u0000\u0000\u0000ce\u0005.\u0000\u0000"+
		"df\u0007\u0003\u0000\u0000ed\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000"+
		"\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hj\u0001\u0000"+
		"\u0000\u0000ic\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000j\u001c"+
		"\u0001\u0000\u0000\u0000kl\u0005/\u0000\u0000lm\u0005*\u0000\u0000mq\u0001"+
		"\u0000\u0000\u0000np\t\u0000\u0000\u0000on\u0001\u0000\u0000\u0000ps\u0001"+
		"\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000"+
		"rt\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000tu\u0005*\u0000\u0000"+
		"uv\u0005/\u0000\u0000vw\u0001\u0000\u0000\u0000wx\u0006\u000e\u0000\u0000"+
		"x\u001e\u0001\u0000\u0000\u0000yz\u0005/\u0000\u0000z{\u0005/\u0000\u0000"+
		"{\u007f\u0001\u0000\u0000\u0000|~\b\u0004\u0000\u0000}|\u0001\u0000\u0000"+
		"\u0000~\u0081\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0001\u0000\u0000\u0000\u0080\u0082\u0001\u0000\u0000\u0000\u0081"+
		"\u007f\u0001\u0000\u0000\u0000\u0082\u0083\u0006\u000f\u0000\u0000\u0083"+
		" \u0001\u0000\u0000\u0000\u0084\u0085\u0005{\u0000\u0000\u0085\u0086\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0006\u0010\u0000\u0000\u0087\"\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\u0005}\u0000\u0000\u0089\u008a\u0001\u0000\u0000"+
		"\u0000\u008a\u008b\u0006\u0011\u0000\u0000\u008b$\u0001\u0000\u0000\u0000"+
		"\u008c\u008d\u0005-\u0000\u0000\u008d\u008e\u0005>\u0000\u0000\u008e\u008f"+
		"\u0001\u0000\u0000\u0000\u008f\u0090\u0006\u0012\u0000\u0000\u0090&\u0001"+
		"\u0000\u0000\u0000\u0091\u0092\u0005:\u0000\u0000\u0092\u0093\u0001\u0000"+
		"\u0000\u0000\u0093\u0094\u0006\u0013\u0000\u0000\u0094(\u0001\u0000\u0000"+
		"\u0000\u0095\u0096\u0005/\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000"+
		"\u0097\u0098\u0006\u0014\u0000\u0000\u0098*\u0001\u0000\u0000\u0000\u0099"+
		"\u009a\u0005;\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0006\u0015\u0000\u0000\u009c,\u0001\u0000\u0000\u0000\u009d\u009e\t"+
		"\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a0\u0006"+
		"\u0016\u0000\u0000\u00a0.\u0001\u0000\u0000\u0000\u00a1\u00a3\u0007\u0005"+
		"\u0000\u0000\u00a2\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a7\u0006\u0017"+
		"\u0000\u0000\u00a70\u0001\u0000\u0000\u0000\u000b\u0000MTV\\agiq\u007f"+
		"\u00a4\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}