package com.samapps.skelet.dataFlow.models.apiModels

import com.google.gson.annotations.SerializedName
import com.samapps.skelet.dataFlow.models.Model


/**
 * Created by sergey on 12/18/17.
 */
data class RegistrationModel(
        @SerializedName("access_token") var accessToken: String?, //TJG-x4dDztqB5N-NBLX2WlaLmmv8_1izp8X_9Pi7p0uzFQjkKxYIxk4kuB1Tb_iQzeMSekgQMPwJI0e_xK11VPvmqd4QSb6Gnit0QaS-fdkROeA1KNB-e1YfsnFQMyrDIiWpgjbn4GFE85qIImB4SGzkJLxxwHkaHO0-b4qTvM87WoILmL6cu20IeukvJGmYvYVujkNs_zJ1FyRaeHfZeFs8mhrrTWnJtyDjZsQsT2UlSgWvZuI42YzZGw5ikD6VgPIjOwOsCG57R3bDIYmUFBZ0lv2vU25WRWt_wsHs4AL4KGgdYaCFBZPWLM1DcYnh3RE6v0cbNbLXR9wM95tyQ5Iad4FV6uhbmHW3SQJWmy6O5Sp6aSTgbC6aTeCLeWKAULruNSzRXSKkgtgzFCO79sl0PQDKzsz3ocTuDWP2vMJX1Jld3Fp7ACtZNC9QGMmJFr3yauQYhRCS6y8qxNMnSYvTFnOA0-8gLqhLaNAQeBwxszLGRZoP4oYER9HM2s05
        @SerializedName("publicKey") var publicKey: String?, //TJG-x4dDztqB5N-NBLX2WlaLmmv8_1izp8X_9Pi7p0uzFQjkKxYIxk4kuB1Tb_iQzeMSekgQMPwJI0e_xK11VPvmqd4QSb6Gnit0QaS-fdkROeA1KNB-e1YfsnFQMyrDIiWpgjbn4GFE85qIImB4SGzkJLxxwHkaHO0-b4qTvM87WoILmL6cu20IeukvJGmYvYVujkNs_zJ1FyRaeHfZeFs8mhrrTWnJtyDjZsQsT2UlSgWvZuI42YzZGw5ikD6VgPIjOwOsCG57R3bDIYmUFBZ0lv2vU25WRWt_wsHs4AL4KGgdYaCFBZPWLM1DcYnh3RE6v0cbNbLXR9wM95tyQ5Iad4FV6uhbmHW3SQJWmy6O5Sp6aSTgbC6aTeCLeWKAULruNSzRXSKkgtgzFCO79sl0PQDKzsz3ocTuDWP2vMJX1Jld3Fp7ACtZNC9QGMmJFr3yauQYhRCS6y8qxNMnSYvTFnOA0-8gLqhLaNAQeBwxszLGRZoP4oYER9HM2s05
        @SerializedName("token_type") var tokenType: String?, //bearer
        @SerializedName("expires_in") var expiresIn: Int?, //2591999
        @SerializedName("role") var role: String?, //NotConfirmed
        @SerializedName(".issued") var issued: String?, //Tue, 23 Jan 2018 15:18:38 GMT
        @SerializedName(".expires") var expires: String? //Thu 22 Feb 2018 15:18:38 GMT
)

