package co.blog.constants;

import springfox.documentation.service.VendorExtension;

import java.util.Collection;
import java.util.Collections;

public class ApiInfoSwaggerConstants {

    public static final String API_KEY_NAME = "JWT";
    public static final String API_KEY_AUTHORIZATION_HEADER = "Authorization";
    public static final String API_KEY_HEADER = "header";

    public static final String AUTHORIZATION_SCOPE = "global";
    public static final String AUTHORIZATION_DESCRIPTION = "accessEverything";
    public static final String SECURITY_REFERENCE = "JWT";


    public static final String TITLE = "Backend Spring Boot Project Blog Application";
    public static final String DESCRIPTION = "Building Real Time REST API`s for Blogging Application built with Core Java(OOp's, package, exception, Lambda, \n" +
            "    stream api etc), Spring Framework(Spring Core, Spring Security, JWT, Spring Data JPA\n" +
            "    (Hibernate) and MySQL Database, IDE Intellij Idea & Developed By Vikash Kumar Basant";
    public static final String VERSION = "v1";
    public static final String TERMS_OF_SERVICE_URL = "Terms Of Service";
    public static final String NAME = "Vikash Kumar Basant";
    public static final String EMAIL = "basantvikash360@gmail.com";
    public static final String URL = "https://github.com/vikashbasant/blog-app-apis/tree/dev";
    public static final String LICENSE = "License Of APIS";
    public static final String LICENSE_URL = "APIs LicenseUrl";
    public static final Collection<VendorExtension> VENDOR_EXTENSIONS = Collections.emptyList();
}
