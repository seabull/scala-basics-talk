# Scala: Beyond the basics 

This repo contains all the resources associated with my talk "Scala: Beyond the basics"

I covers various aspects comparing it to Java. Highlighting where language features can be used
instead of boiler plate and reflection.

## Generating

`sbt paradox` will generate the presentation and place it in
`target/paradox/main/site`

The presentation itself is a [paradox](https://github.com/lightbend/paradox)
markdown file in `src/main/paradox/index.md`. Code snippets are taken from
`src/main/java` and `src/main/scala`.

The paradox theme in `src/main/paradox/_theme` formats the output as a
[reveal.js](https://revealjs.com) HTML presentation. This could be split out
as a separate deliverable if it proves useful. The reveal.js theme in
`src/main/paradox/_template/css/theme/akka.css adds some Akka fonts and colors.

A pre-built version of the slide are [here](http://batey.info/talks/scala-basics/#/)

## TODO

Possible extras:
* Existential types
  - Abstract type members vs type parameters
* Maths implicit example
* Dependently pathed types
* Dependent types
* Embedded DSLs

