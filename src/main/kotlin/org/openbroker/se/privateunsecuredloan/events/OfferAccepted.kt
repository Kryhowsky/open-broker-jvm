package org.openbroker.se.privateunsecuredloan.events

import org.openbroker.se.model.BankAccount
import org.openbroker.common.model.Reference
import org.openbroker.common.obfuscateDigits
import org.openbroker.common.requireMin

data class OfferAccepted @JvmOverloads constructor(
    override val brokerReference: Reference,
    val offerId: Reference? = null,
    val bankAccount: BankAccount? = null,
    val requestedCredit: Int? = null,
    val termMonths: Int? = null,
    val ssn: String? = null,
    val ssnCoapplicant: String? = null,
    val emailAddress: String? = null,
    val emailAddressCoapplicant: String? = null
) : PrivateUnsecuredLoanEvent {
    init {
        requestedCredit.requireMin(1, "requestedCredit")
        termMonths.requireMin(1, "termMonths")

        val ssnRegex = Regex("^[0-9]{12}$")
        ssn?.let {
            require(it.matches(ssnRegex)) {
                "Invalid applicant's SSN: '${obfuscateDigits(it)}'"
            }
        }
        ssnCoapplicant?.let {
            require(it.matches(ssnRegex)) {
                "Invalid co-applicant's SSN: '${obfuscateDigits(it)}'"
            }
        }
    }
}