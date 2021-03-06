/*
 * Copyright 2013 Nokia Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nokia.util.avro.types;

import org.codehaus.jackson.annotate.JsonValue;

import java.util.Set;

import static com.nokia.util.avro.types.AvroPrimitive.PrimitiveType.NULL;
import static java.util.Collections.emptySet;

/**
 * Class which represents an Avro primitive type.
 * http://avro.apache.org/docs/current/spec.html#schema_primitive
 *
 * @author Ben Fagin (Nokia)
 * @version 01-12-2012
 */
public class AvroPrimitive extends AvroType {

    public static final AvroPrimitive NULL_TYPE = NULL.newInstance();

    public enum PrimitiveType {
        NULL("null", "null"),
        BOOLEAN("boolean", "true"),
        INT("int", "0"),
        INTEGER("integer", "0"),
        LONG("long", "0"),
        FLOAT("float", "0.0"),
        DOUBLE("double", "0.0"),
        STRING("string", ""),
        BYTES("bytes", "\\\\u00FF"),   // this is a byte buffer
        BYTE("int", "0");              // avro has no concept of a single byte

        public final String name;
        public final String defaultValue;

        PrimitiveType(String name, String defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }

        public AvroPrimitive newInstance() {
            return new AvroPrimitive(this);
        }
    }

    private String name;

    public AvroPrimitive(PrimitiveType type) {
        this.name = type.name;
        this.defaultValue = type.defaultValue;
    }

    public static AvroPrimitive fromType(String type) {
        PrimitiveType value = null;

        for (PrimitiveType primitiveType : PrimitiveType.values()) {
            if (primitiveType.name().equals(type.toUpperCase())) {
                value = primitiveType;
                break;
            }
        }

        if (value == null) {
            throw new RuntimeException("unsupported primitive type: " + type);
        }

        return new AvroPrimitive(value);
    }

    @JsonValue
    public String getType() {
        return name;
    }

    @Override
    public Set<String> getDependencies() {
        return emptySet();
    }
}