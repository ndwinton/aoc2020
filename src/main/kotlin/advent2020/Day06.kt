package advent2020

fun distinctQuestionsInGroup(group: String) =
    group.filter { it in 'a' .. 'z' }.toSet().size

fun totalDistinctForGroups(input: String) =
    input.split("\n\n").fold(0) { total, group -> 
        total + distinctQuestionsInGroup(group)
    }

fun commonQuestionsInGroup(group: String) =
    group.split("\n").map { it.toSet() }.let {
        it.fold(it.first()) { result, set -> result.intersect(set) }
    }.size

fun totalCommonForGroups(input: String) =
    input.split("\n\n").fold(0) { total, group ->
        total + commonQuestionsInGroup(group)
    }