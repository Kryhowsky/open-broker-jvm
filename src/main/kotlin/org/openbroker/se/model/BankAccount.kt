package org.openbroker.se.model

import org.openbroker.common.obfuscateDigits

private val clearingNumberRegex = Regex("^[1-9][0-9]{3,4}$")
private val accountNumberRegex = Regex("^[0-9]{1,11}$")

/**
 * A Swedish bank account consisting of a clearing number and an
 * account number
 */
data class BankAccount(
    /**
     * The clearing number, also known as the sort code identifies, the
     * bank along with the branch
     */
    val clearingNo: String,
    /**
     * The account number within the bank identified by the
     * clearingNo. Specified using digits only.
     */
    val accountNo: String
) {
    init {
        require(clearingNo.matches(clearingNumberRegex)) {
            "Invalid clearing number with format '${obfuscateDigits(clearingNo)}'"
        }
        require(accountNo.matches(accountNumberRegex)) {
            "Invalid account number with format '${obfuscateDigits(accountNo)}'"
        }
    }
}