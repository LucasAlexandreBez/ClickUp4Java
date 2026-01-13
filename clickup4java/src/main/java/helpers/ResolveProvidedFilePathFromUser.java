package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Resolves the {@link Path} for files paths provided by user.
 * 
 * @author Lucas Alexandre Bez Piancoski
 * @since 1.0.0
 */
public class ResolveProvidedFilePathFromUser {
	
	private ResolveProvidedFilePathFromUser() {}
	
	public static boolean checkIfFileExist(Path file_path) throws IOException {
	    if (!Files.exists(file_path)) {
	        return false;
	    }
	    return true;
	}
	
	public static Path getFile(String file_path) throws IOException {
	    Path path = Paths.get(file_path);
	    if (!checkIfFileExist(path)) throw new IOException("File does not exist: " + path);
	    return path;
	}
}
