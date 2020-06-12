package org.acme;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = { Book.class, Author.class }, schemaPackageName = "book_sample")
interface BookContextInitializer extends SerializationContextInitializer {
}