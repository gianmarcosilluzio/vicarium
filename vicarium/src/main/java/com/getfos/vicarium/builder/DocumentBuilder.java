package com.getfos.vicarium.builder;

import com.getfos.vicarium.model.external.DocumentExternal;

public class DocumentBuilder {
	
	public static String buildDocument(DocumentExternal documentExternal) {
		return documentExternal.getPdf().getValue();
	}
}
