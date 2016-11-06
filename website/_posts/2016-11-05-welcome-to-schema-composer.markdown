---
layout: post
title:  "Welcome to Schema Composer for Oracle™ SQL*Developer Data Modeler"
date:   2016-11-05 16:52:06 +0000
categories: release
---

Welcome to my Pre-Release of Schema Composer.

Schema Composer allows you to convert the Logical Enterprise Data Models you have developed in Data Modeler to a hierarchical form.  At the moment the current formats are:
- XSD
- JSON Schema
  
but I intend to add more over time, as well as different variations of these.

This initial release is really a proof of concept release, and shouldn't be used for any production or critial systems.  It also has a number of known issues, including:
- It doesn't support non-exclusive sub-types.
- XSD's uses `<xs:choice>` with no option to use base types.
- JSON Schema's don't enforce sub-types correctly.
- List of Values/Range is not supported.
- Only Attribute comments are supported.
- Mandatory Entities not working for JSON Schema.
- The UI really needs a lot of work - and doesn't enforce rules which could lead to invalid output.
- Only English is supported.

Also, it's only been tested on with Data Modeler 4.1.5 with Java 8; basically, expect some issues.

If this hasn't scared you off and you want to try it out:

- If you haven't already, download Data Modeler here : <http://www.oracle.com/technetwork/developer-tools/datamodeler/overview/index.html>
- Start Data Modeler, Select Help &gt; "Check for Updates" and Click "Add" and use the following settings:
	- Name: Schema Composer
	- URL : http://schemacomposer.xsynergy.com/downloads/updatecenter.xml


You can also watch [this video](/assets/introduction.mp4) which should get you started quickly.

#### Background

I was moving my Data Models from ERWin to Data Modeler and I needed to export some subviews into a very particular Excel format; a format that I couldn't re-create with the built in Reports in Data Modeler. So, I wrote a little extension to suit my needs, <https://github.com/colinfitzpatrick/datamodeler-excel-exporter>.

Some time later, I needed an XSD from the data model, so I extended the original idea ...   

