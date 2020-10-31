/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.datatypes;

public final class ActionResult {

    public ResultType resultType;
    public String message;

    public ActionResult(ResultType resultType) {
        this.resultType = resultType;
        this.message = null;
    }

    public ActionResult(ResultType resultType, String message) {
        this.resultType = resultType;
        this.message = message;
    }

    public enum ResultType {
        SUCCESS, NOTHING_CHANGED, ERROR
    }
}
