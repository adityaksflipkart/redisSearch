package com.ind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public class Constants {
	public static final String DELIMITER_LIST = "['''\\+\\-*/\\^:;\\\\()#@$%&,.?{ }!='|'\'\"<>\\[\\]]+,~`";
	public static AtomicLong fileId = new AtomicLong(0);
	public static final String IMAGE_PATTERN_AND_ENCODINGFILE = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|ico|rdb))$)";
	public static final Pattern PATTERN = Pattern.compile(IMAGE_PATTERN_AND_ENCODINGFILE);

	public final static long getnewFileId() {
		return fileId.getAndIncrement();
	}

	private static String[] stopWords = { "", "a", "able", "about", "above", "according", "accordingly", "across",
			"actually", "after", "afterwards", "again", "against", "all", "allow", "allows", "almost", "alone", "along",
			"already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody",
			"anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate",
			"appropriate", "are", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away",
			"awfully", "b", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand",
			"behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both",
			"brief", "but", "by", "c", "came", "can", "cannot", "cant", "cause", "causes", "certain", "certainly",
			"changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering",
			"contain", "containing", "contains", "corresponding", "could", "course", "currently", "d", "definitely",
			"described", "despite", "did", "different", "do", "does", "doing", "done", "down", "downwards", "during",
			"e", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et",
			"etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly",
			"example", "except", "f", "far", "few", "fifth", "first", "five", "followed", "following", "follows", "for",
			"former", "formerly", "forth", "four", "from", "further", "furthermore", "g", "get", "gets", "getting",
			"given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "h", "had", "happens",
			"hardly", "has", "have", "having", "he", "hello", "help", "hence", "her", "here", "hereafter", "hereby",
			"herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how",
			"howbeit", "however", "i", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed",
			"indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "its",
			"itself", "j", "just", "k", "keep", "keeps", "kept", "know", "knows", "known", "l", "last", "lately",
			"later", "latter", "latterly", "least", "less", "lest", "let", "like", "liked", "likely", "little", "look",
			"looking", "looks", "ltd", "m", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely",
			"might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "n", "name", "namely", "nd",
			"near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine",
			"no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "o",
			"obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto",
			"or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over",
			"overall", "own", "p", "particular", "particularly", "per", "perhaps", "placed", "please", "plus",
			"possible", "presumably", "probably", "provides", "q", "que", "quite", "qv", "r", "rather", "rd", "re",
			"really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "s",
			"said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed",
			"seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven",
			"several", "shall", "she", "should", "since", "six", "so", "some", "somebody", "somehow", "someone",
			"something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify",
			"specifying", "still", "sub", "such", "sup", "sure", "t", "take", "taken", "tell", "tends", "th", "than",
			"thank", "thanks", "thanx", "that", "thats", "the", "their", "theirs", "them", "themselves", "then",
			"thence", "there", "there's", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon",
			"these", "they", "they'd", "they'll", "they're", "they've", "think", "third", "this", "thorough",
			"thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too",
			"took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "u", "un", "under",
			"unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses",
			"using", "usually", "uucp", "v", "value", "various", "very", "via", "viz", "vs", "w", "want", "wants",
			"was", "way", "we", "welcome", "well", "went", "were", "what", "whatever", "when", "whence", "whenever",
			"where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which",
			"while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with",
			"within", "without", "wonder", "would", "would", "x", "y", "yes", "yet", "you", "your", "yours", "yourself",
			"yourselves", "z", "zero", "nbsp", "ref", "math", "category", "area", "time", "refer", "population", "land",
			"total", "people", "size", "km", "year", "densities", "state", "unit", "water", "live", "location", "house",
			"made", "image", "countries", "families", "ag", "line", "official", "type", "american", "reside", "website",
			"white", "children", "region", "it", "map", "marriage", "include", "individual", "settlement", "census",
			"mi", "older", "male", "couple", "average", "female", "demographical", "long", "household", "code", "title",
			"income", "geographical", "counti", "median", "racial", "poverti", "subdivision", "footnotes", "list",
			"coordinates", "marriage", "capita", "bureau", "makeup", "versus", "offset", "utc", "timezone", "longd",
			"latd", "dst", "latn", "longew", "present", "mapsiz", "sq", "zone", "caption", "spread", "govern",
			"establish", "date", "mile", "longm", "latm", "inform", "name", "lat", "squar", "flag", "leader", "husband",
			"polit", "skylin", "process", "standard", "images", "elev", "feder", "system", "seal", "info", "featur",
			"divis", "postal", "ft", "id", "blank", "magnitud", "fip", "gener", "geograph", "nicknam", "north", "gni",
			"motto", "race", "imag", "type", "land", "total", "water", "size", "density", "region", "map", "website",
			"settlement", "offset", "utc", "timezone", "dst", "caption", "latitude", "flag", "official", "reside",
			"unit", "miles", "demographical", "geographical", "footnot", "median", "coordin", "subdivis", "elevation" };
	        public static Set<String> STOP_WORDS = new HashSet<String>(Arrays.asList(stopWords));
}
