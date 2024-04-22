import java.util.*;

class FileVersion {
    private String content;
    private List<FileVersion> deltas;

    public FileVersion(String content) {
        this.content = content;
        this.deltas = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }

    public void addDelta(FileVersion delta) {
        deltas.add(delta);
    }

    public List<FileVersion> getDeltas() {
        return deltas;
    }
}

public class FileVersioningSystem {
    private FileVersion baseVersion;

    public FileVersioningSystem(String baseContent) {
        this.baseVersion = new FileVersion(baseContent);
    }

    public void addDelta(FileVersion parentVersion, FileVersion delta) {
        parentVersion.addDelta(delta);
    }

    public String generateVersionContent(FileVersion version) {
        StringBuilder sb = new StringBuilder(version.getContent());
        for (FileVersion delta : version.getDeltas()) {
            applyDelta(sb, delta);
        }
        return sb.toString();
    }

    private void applyDelta(StringBuilder content, FileVersion delta) {
        // Apply delta logic, for example, appending changes to content
        content.append(delta.getContent());
    }

    // Method to generate any version
    public String generateAnyVersionContent(FileVersion targetVersion) {
        return generateVersionContent(targetVersion);
    }

    public static void main(String[] args) {
        FileVersioningSystem versioningSystem = new FileVersioningSystem("Base Content");

        // Creating deltas
        FileVersion delta1 = new FileVersion("Delta 1 Content");
        FileVersion delta2 = new FileVersion("Delta 2 Content");

        // Adding deltas to the base version
        versioningSystem.addDelta(versioningSystem.baseVersion, delta1);
        versioningSystem.addDelta(delta1, delta2);

        // Generating a specific version content
        String versionContent = versioningSystem.generateAnyVersionContent(delta2);
        System.out.println("Version Content: " + versionContent);
    }
}
