import com.rrbofficial.androiduipracticekotlin.MYSQL.ApiResponse
import com.rrbofficial.androiduipracticekotlin.MYSQL.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("sign_up.php")
    suspend fun signUp(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("hobby") hobby: String,
        @Field("degree") degree: String,
        @Field("profile_picture") profile_picture: String
    ): Response<ApiResponse>

    @FormUrlEncoded
    @POST("update_user_profile.php")
    suspend fun updateUserProfile(
        @Field("email") email: String,
        @Field("hobby") hobby: String,
        @Field("degree") degree: String,
        @Field("profile_picture") profile_picture: String?
    ): Response<ApiResponse>

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("user_id_or_email") userIdOrEmail: String,
        @Field("password") password: String
    ): Response<LoginResponse>
}
