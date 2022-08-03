package com.javacodegeeks.aws;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) 
	{
		if (args.length == 0) 
		{
			System.err.println("Please provide at least one argument.");
			return;
		}
		switch (args[0]) 
		{
			case "detect-labels":
				DetectLabels detectLabels = new DetectLabels();
				detectLabels.run(args);
				break;

			case "detect-faces":
				DetectFaces detectFaces = new DetectFaces();
				detectFaces.run(args);
				break;

			case "compare-faces":
				CompareFaces compareFaces = new CompareFaces();
				compareFaces.run(args);
				break;

				default:
				System.err.println("Unknown argument: " + args[0]);
				return;
		}
    }
}
