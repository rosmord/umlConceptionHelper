# UmlSequenceToClassConverter for GLG 204

This is a software specifically designed for the [GLG 204](https://informatique.cnam.fr/fr/spip.php?rubrique96) course I teach at the [Conservatoire National des Arts et Métiers](https://www.cnam.fr).

I don't like interactive diagram drawing much, and I'm more at ease with raw text.
Hence, I have used [`plantuml`](https://plantuml.com/fr/) and Markdown quite a lot in preparing the documentation for this course.

Some work on the UML diagram contains a rather mecanical part, in particular when we extract information from sequence diagrams and retranscribe it into class diagrams.

The current software makes it automatically. It's somehow a quick hack, and more work could make it more useful.


## Running

Well, `./gradlew run` should work.

The jar which is created in `./build/libs` can also be run with `java -jar`.

## TODO

- add a field for the original class diagram, which should be used as basis
- automatically remove intermediary `@startuml` and `@enduml` tags ?
- add another panel for global class diagram consolidation.

## Future

Allow the use of the whole markdown file as entry. In this case, 
it should be possible to select what diagrams to use as basis,
and then to copy the result.
