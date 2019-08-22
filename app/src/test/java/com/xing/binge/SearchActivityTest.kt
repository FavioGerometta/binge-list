package com.xing.binge

import com.xing.binge.util.search
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchActivityTest {

    /**
     * Test extension function [search] as seen used on [SearchActivity.genreSet].
     */
    val set = HashSet<String>()

    @Before
    fun init(){
        set.add("action")
        set.add("science fiction")
        set.add("international movies")
    }

    @Test
    fun test_when_validSingleWordedSearchTextIsProvided_then_rightMatchIsFound() {
        val resultPair  = set.search("action")
        assertEquals(resultPair.first, true)
        assertEquals(resultPair.second, "action")
    }

    @Test
    fun test_when_invalidSingleWordedSearchTextIsProvided_then_noRightMatchIsFound() {
        val resultPair  = set.search("international")
        assertEquals(resultPair.first, false)
        assertEquals(resultPair.second, null)
    }

    @Test
    fun test_when_validDoubleWordedSearchTextIsProvided_then_rightMatchIsFound() {
        val resultPair  = set.search("international movies")
        assertEquals(resultPair.first, true)
        assertEquals(resultPair.second, "international movies")
    }

    @Test
    fun test_when_invalidDoubleWordedSearchTextIsProvided_then_noRightMatchIsFound() {
        val resultPair  = set.search("science movies")
        assertEquals(resultPair.first, false)
        assertEquals(resultPair.second, null)
    }

    @Test
    fun test_when_invalidDoubleWordedSearchTextIsProvided_and_firstWordIsValid_then_rightMatchIsFound() {
        val resultPair  = set.search("action movies")
        assertEquals(resultPair.first, true)
        assertEquals(resultPair.second, "action")
    }

}