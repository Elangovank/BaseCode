package com.truelife.http

/**
 * Created by Mathan on 28/01/19.
 **/

interface ResponseListener {

    /**
     * @param r - The model class that is passed on the parser
     */
    fun onResponse(r: Response?)
}
