package com.nokia.util.avro.types;

import com.sun.tools.xjc.model.*;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

public class XSDTypeIntrospector {

    private static final String NOT_DEFINED = "NOT_DEFINED";

    public static String getXSDType(CEnumLeafInfo info) {
        StringBuilder sb = new StringBuilder();
        sb.append("XSD Type:");
        sb.append(ofNullable(info.getTypeName()).map(String::valueOf)
                .orElseGet(() -> NOT_DEFINED));

        if (isNotEmpty(info.javadoc) && info.javadoc.length() <= 30) {
            sb.append(", ");
            sb.append(info.javadoc);
        }
        return sb.toString();
    }

    public static String getXSDType(CClassInfo info) {
        StringBuilder sb = new StringBuilder();
        sb.append("XSD Type:");
        sb.append(ofNullable(info.getTypeName()).map(String::valueOf)
                .orElseGet(() -> NOT_DEFINED));

        if (isNotEmpty(info.javadoc) && info.javadoc.length() <= 30) {
            sb.append(", ");
            sb.append(info.javadoc);
        }
        return sb.toString();
    }

    public static String getXSDType(CPropertyInfo info) {
        StringBuilder sb = new StringBuilder();
        sb.append("XSD Type:");

        if (CElementPropertyInfo.class.isAssignableFrom(info.getClass())) {
            sb.append(((CElementPropertyInfo) info).getTypes()
                    .stream()
                    .findAny()
                    .map(t ->
                            ofNullable(t.getTypeName())
                                    .map(String::valueOf)
                                    .orElseGet(
                                            () -> ofNullable(t.getTagName())
                                                    .map(String::valueOf).orElseGet(() -> NOT_DEFINED))
                    ).orElseGet(() -> NOT_DEFINED)
            );
        } else if (CAttributePropertyInfo.class.isAssignableFrom(info.getClass())) {
            sb.append(ofNullable(((CAttributePropertyInfo) info).getTarget().getTypeName())
                    .map(String::valueOf).orElseGet(() -> NOT_DEFINED));
        }

        if (isNotEmpty(info.javadoc) && info.javadoc.length() <= 30) {
            sb.append(", ");
            sb.append(info.javadoc);
        }
        return sb.toString();
    }
}
