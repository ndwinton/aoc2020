# Advent of Code 2020

See https://adventofcode.com/2020/

The core logic for each day is in a separate file, but everything can be
run from the main program.

My solutions are in Kotlin, because I like the language and want to
practice it more.

There are unit tests which sometimes go beyond the strict requirements of
the AoC specifications or inputs. For example, on day 1 it handles the case
of duplicate values appearing in the input, although no such values actually
appeared in the supplied file.

I like to try to find a generally functional-style solution, even if there
may be a simpler procedural approach because, again, it's good practice.
I hope that things are reasonably readable. I aim to keep functions short
and to name things clearly (well, clearly to me ...).

As I've gone on I've also decided to stick to a self-imposed constraint
of making everything purely functional with no mutable state -- there
shouldn't be a single `var` in sight ... That has definitely stretched
me on occasions and meant that it's taken me rather longer to solve
some things than it might otherwise have done.

I'm also experimenting a bit with different Kotlin idioms, so there might
be different styles from day-to-day. For example, on day 3 I was going for
a recursive solution and on day 4 I made everything an extension function,
for no very profound reason.
