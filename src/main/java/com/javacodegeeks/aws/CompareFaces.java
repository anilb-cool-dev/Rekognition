package com.javacodegeeks.aws;

import com.amazonaws.services.rekognition.model.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CompareFaces
{
    public void run(String[] args)
    {
        if (args.length < 3)
        {
            System.err.println("Please provide two images:  .");
            return;
        }

        ByteBuffer image1 = loadImage(args[1]);
        ByteBuffer image2 = loadImage(args[2]);
        if (image1 == null || image2 == null) {
            return;
        }

        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(new Image().withBytes(image1))
                .withTargetImage(new Image().withBytes(image2))
                .withSimilarityThreshold(70F);

        CompareFacesResult result = ClientFactory.createClient().compareFaces(request);

        List<CompareFacesMatch> faceMatches = result.getFaceMatches();
        for (CompareFacesMatch match : faceMatches) {
            Float similarity = match.getSimilarity();
            System.out.println("Similarity: " + similarity);
        }
    }

    private ByteBuffer loadImage(String imgPath) {
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(imgPath));
        } catch (IOException e) {
            System.err.println("Failed to load image: " + e.getMessage());
            return null;
        }
        return ByteBuffer.wrap(bytes);
    }
}
