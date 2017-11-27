package uk.co.alt236.apkdetails.repo;

import uk.co.alt236.apkdetails.repo.common.Entry;
import uk.co.alt236.apkdetails.repo.common.ZipContents;

import java.util.*;

public class ArchitectureRepository {
    private static final String JNI_DIRECTORY = "lib/";
    private final ZipContents zipContents;

    public ArchitectureRepository(ZipContents zipContents) {
        this.zipContents = zipContents;
    }

    public List<String> getJniArchitectures() {
        final List<Entry> libFiles = zipContents
                .getEntries(entry -> entry.getName().startsWith(JNI_DIRECTORY));

        final Set<String> architectures = new HashSet<>();

        for (final Entry entry : libFiles) {
            final String cleanName = entry.getName().substring(JNI_DIRECTORY.length(), entry.getName().length());

            if (!entry.isDirectory()) {
                architectures.add(cleanName.substring(0, cleanName.indexOf("/")));
            }
        }

        final List<String> retVal = new ArrayList<>(architectures);
        Collections.sort(retVal);
        return retVal;
    }
}