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

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Set;

import static java.util.Collections.emptySet;

/**
 * Class which represents an Avro array type.
 * http://avro.apache.org/docs/current/spec.html#Arrays
 *
 * @author Ben Fagin (Nokia)
 * @version 01-12-2012
 */
public class AvroArray extends AvroType {

    @JsonProperty
    public final String type = "array";

    @JsonProperty
    private final AvroType items;

    public AvroArray(AvroType itemType) {
        this.items = itemType;
        defaultValue = "[]";
    }

    @Override
    public Set<String> getDependencies() {
        if (items != null) {
            return items.getDependencies();
        } else {
            return emptySet();
        }
    }
}