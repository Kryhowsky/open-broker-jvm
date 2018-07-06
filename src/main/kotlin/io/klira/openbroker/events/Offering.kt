package io.klira.openbroker.events

import io.klira.openbroker.model.LoanInsuranceOffer
import io.klira.openbroker.model.Offer
import io.klira.openbroker.model.Reference

/**
 * An offer for a loan
 */
data class Offering(
    override val brokerReference: Reference,
    val offer: Offer,
    val loanInsurance: LoanInsuranceOffer? = null
): OpenBrokerEvent