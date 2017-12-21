# DynamicLayoutInflater
[![Download](https://api.bintray.com/packages/truenight/maven/dynamic-inflater/images/download.svg) ](https://bintray.com/truenight/maven/dynamic-inflater/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/xyz.truenight.dynamic/inflater/badge.svg)](https://maven-badges.herokuapp.com/maven-central/xyz.truenight.dynamic/inflater)
[![Licence](https://img.shields.io/badge/Licence-Apache2-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Layout inflater with ``String`` as source of layout

# Overview

Allows to inflate layout from remote source (e.g. backend)

# Installation

Add dependency to your `build.gradle` file:

```groovy
dependencies {
    implementation 'xyz.truenight.dynamic:inflater:1.0.2'
    // or AppCompatSupport implementation 'xyz.truenight.dynamic:compat:1.0.2'
}
```

or to your `pom.xml` if you're using Maven:

```xml
<dependency>
  <groupId>xyz.truenight.dynamic</groupId>
  <artifactId>inflater</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>
```
# Usage

## Creating base instance

``DynamicLayoutInflater`` like ``LayoutInflater`` uses Prototype pattern.

```java
  // creating base instance which will be accessed via ``WeakReference`` 
  // so you need to store strong reference on object returned from create()
  inflater = CompatDynamicLayoutInflater.base(this)
                .registerAttrAdapter(...)
                .registerParamAdapter(...)
                ...
                .create();
```

## Inflate

```java
  View view = DynamicLayoutInflater.from(context).inflate(resource, parent);
```

## Register ``AttrAdapter``

```java

```

## Register ``ParamAdapter``

```java

```
