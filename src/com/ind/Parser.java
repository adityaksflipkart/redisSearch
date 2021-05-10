package com.ind;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

public class Parser {

	public static void parse(String path) {
		Path startPath = Paths.get(path).normalize();
		System.out.println("loaded parser");
		FileVisitor counter = new FileVisitor();
		try {
			Files.walkFileTree(startPath, counter);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static final class FileVisitor extends SimpleFileVisitor<Path> {
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

			Matcher matcher = Constants.PATTERN.matcher(file.getFileName().toString());
			if (matcher.matches()) {
				return FileVisitResult.CONTINUE;
			}

			Stemmer stemmer = new Stemmer();
			BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
			String line = "";
			long fileId = Constants.getnewFileId();
			long counter = 0;
			while ((line = br.readLine()) != null) {
				Indexes.addLineToFile(fileId + "#" + file.getFileName().toString(), counter, line);

				StringTokenizer st = new StringTokenizer(line, Constants.DELIMITER_LIST);

				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					token = token.replaceAll("[^0-9\\x00-\\x7f]", "").replaceAll("\\s+", "").replaceAll("^\\d*", "");
					String word = token.toLowerCase();

					if (!word.isEmpty() && word.length() >= 3 && !Constants.STOP_WORDS.contains(word)) {
						
						stemmer.add(word.toCharArray(), word.length());
						stemmer.stem();
						word = stemmer.toString();

						Indexes.addWordToIndex(word, fileId + "#" + file.getFileName().toString(), counter);
					}
				}
				counter++;
			}

			br.close();
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
			if (e == null) {
				return FileVisitResult.CONTINUE;
			} else {
				throw e;
			}
		}

	}
}
