package com.mediSlot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHashing {
	 private static final String HASH_ALGORITHM = "SHA-256"; // Using SHA-256 algorithm
	    private static final int SALT_LENGTH = 16; // Length of the salt (in bytes)
	    // Method to hash the password using SHA-256 with salt
	    public static String hashPassword(String password) {
	        try {
	            // Generate a random salt
	            SecureRandom random = new SecureRandom();
	            byte[] salt = new byte[SALT_LENGTH];
	            random.nextBytes(salt);

	            // Initialize the MessageDigest with SHA-256 algorithm
	            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);

	            // Add the salt to the digest
	            digest.update(salt);

	            // Perform multiple iterations of hashing
	            byte[] hashedPassword = digest.digest(password.getBytes());

	            // Convert the hashed password and salt to Base64 encoded strings
	            String encodedSalt = Base64.getEncoder().encodeToString(salt);
	            String encodedPassword = Base64.getEncoder().encodeToString(hashedPassword);

	            // Concatenate the encoded password and salt (separated by a delimiter)
	            return encodedPassword + ":" + encodedSalt;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return null; // Handle the error appropriately
	        }
	    }

	    // Method to verify a plaintext password against a hashed password
	    public static boolean verifyPassword(String plaintextPassword, String hashedPassword) {
	        try {
	            // Split the hashed password into encoded password and salt
	            String[] parts = hashedPassword.split(":");
	            String encodedPassword = parts[0];
	            String encodedSalt = parts[1];

	            // Decode the salt and password from Base64
	            byte[] salt = Base64.getDecoder().decode(encodedSalt);
	            byte[] storedPassword = Base64.getDecoder().decode(encodedPassword);

	            // Initialize the MessageDigest with SHA-256 algorithm
	            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);

	            // Add the decoded salt to the digest
	            digest.update(salt);

	            // Perform multiple iterations of hashing
	            byte[] hashedInput = digest.digest(plaintextPassword.getBytes());

	            // Compare the hashed input with the stored password
	            return MessageDigest.isEqual(storedPassword, hashedInput);
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return false; // Handle the error appropriately
	        }
	    }


}
