package simple.web.blog.web;

import aQute.bnd.annotation.headers.RequireCapability;

public @interface Requieres {

    @RequireCapability(ns = "bootstrap.css", filter = "${frange;3.2.0}")
    public @interface Bootstrap {}
    
    @RequireCapability(ns = "angular.js", 	 filter = "${frange;1.2.21}")
    public @interface AngularJS {}
    
}
