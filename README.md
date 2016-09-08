# Artifact for _Java and Scala's Type Systems are Unsound: The Existential Crisis of Null Pointers_

We provide the three programs as listed in the paper (Amin and Tate, OOPSLA'16 _forthcoming_),
and step-by-step instructions on how to compile and run them.

- `Unsound.java` for Java 8
- `Unsound9.java` for Java 9
- `unsound.scala` for Scala

These short programs demonstrate the unsoundness of Java and
Scala’s current type systems. In particular, these programs provide
parametrically polymorphic functions that can turn any type into any
type without casting. Fortunately, parametric polymorphism was not
integrated into the Java Virtual Machine (JVM), so these examples do
not demonstrate any unsoundness of the JVM

## Getting Started Guide

You need JDK 8, JDK 9, Scala 2.11.8.
You need to be able to use `javac`, `java`, `scalac`, `scala`.
You need to be able to switch between JDK 8 and JDK 9.
We give further pointers within the step by step instructions below.

## Step-by-Step Instructions

### Java

#### JDK 8 ([Download](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html))

##### Step 1: Check version
`java -version`

```
java version "1.8.0_25"
Java(TM) SE Runtime Environment (build 1.8.0_25-b17)
Java HotSpot(TM) 64-Bit Server VM (build 25.25-b02, mixed mode)
```

If necessary, switch to JDK 8:
`export JAVA_HOME=$(/usr/libexec/java_home -v 1.8) ; PATH=$JAVA_HOME/bin:$PATH`

##### Step 2: Compile
`javac Unsound.java`

_(compiles OK)_

##### Step 3: Run
`java Unsound`

```
Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
  at Unsound.main(Unsound.java:15)
```

#### JDK 9 ([Download](https://jdk9.java.net/download/))

##### Step 1: Check version
`java -version`

```
java version "9-ea"
Java(TM) SE Runtime Environment (build 9-ea+121)
Java HotSpot(TM) 64-Bit Server VM (build 9-ea+121, mixed mode)
```

If necessary, switch to JDK 9:
`export JAVA_HOME=$(/usr/libexec/java_home -v 9) ; PATH=$JAVA_HOME/bin:$PATH`

##### Step 2: Compile
`javac Unsound9.java`

_(compiles OK)_

Note that `Unsound.java` does not compile with JDK 9.

`javac Unsound.java`


```
Unsound.java:12: error: method upcast in class Bind<A> cannot be applied to given types;
    return bind.upcast(constrain, t);
               ^
  required: Constrain<U,B>,B
  found: Constrain<U,CAP#1>,T
  reason: inference variable B has incompatible bounds
    upper bounds: U
    lower bounds: T
  where U,T,B,A are type-variables:
    U extends Object declared in method <T,U>coerce(T)
    T extends Object declared in method <T,U>coerce(T)
    B extends U declared in method <B>upcast(Constrain<A,B>,B)
    A extends Object declared in class Bind
  where CAP#1 is a fresh type-variable:
    CAP#1 extends U super: T from capture of ? super T
1 error
```

##### Step 3: Run
`java Unsound9`

```
Exception in thread "main" java.lang.ClassCastException: java.lang.Integer (in module: java.base) cannot be cast to java.lang.String (in module: java.base)
  at Unsound9.main(Unsound9.java:25)
```

### Scala ([Download](http://downloads.lightbend.com/scala/2.11.8/scala-2.11.8.tgz))

##### Step 1: Check version
`scala -version`

```
Scala code runner version 2.11.8 -- Copyright 2002-2016, LAMP/EPFL
```

`java -version`

```
java version "1.8.0_25"
Java(TM) SE Runtime Environment (build 1.8.0_25-b17)
Java HotSpot(TM) 64-Bit Server VM (build 25.25-b02, mixed mode)
```

Scala does not support JDK 9 yet, so switch back to JDK 8 if necessary.
You can also switch back to an earlier JDK, e.g. 6, to try the steps with more previous versions of Scala ([download list](http://scala-lang.org/download/all.html)).
The earliest version we could get to work with JDK 8 is 2.10.2, and all the releases we sampled in between up to the current milestone 2.12.0-M4 reproduce the remaining steps.
The example does not compile in Scala 2.9.3 and earlier due to restrictions on dependent method types. In `unsound_legacy.scala`, we give an example without dependent method types that exhibits the same issue using only features already available in Scala 2.5.0.

##### Step 2: Compile
`scalac unsound.scala`

_(compiles OK)_

##### Step 3: Run

`scala unsound`

```
java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
  at unsound$.main(unsound.scala:14)
  at unsound.main(unsound.scala)
  at ...
```
