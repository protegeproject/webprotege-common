package edu.stanford.protege.webprotege.common;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-28
 *
 * Represents the location of a blob in a bucket.
 * @param bucket The name of the bucket
 * @param name The name of the blob.  This is a hierarchical path separated by forward slashes.
 */
public record BlobLocation(String bucket, String name) {

}
