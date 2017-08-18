package be.hogent.Eva2017g5.EVAH5.domainAndModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sofie.
 */

public class EvaUser implements Parcelable{
    @SerializedName("_id")
    private long id;

    @SerializedName("firstname")
    private String firstName;
    @SerializedName("lastname")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    private List<Challenge> completedChallenges;

    private List<Challenge> todaysChallenge;

    private List<Recipe> myRecipes;

    private List<Recipe> favoriteRecipes;

    private String todaysChallengeId;

    protected EvaUser(Parcel in) {
        id = in.readLong();
        username = in.readString();
        todaysChallengeId = in.readString();
    }

    public static final Creator<EvaUser> CREATOR = new Creator<EvaUser>() {
        @Override
        public EvaUser createFromParcel(Parcel in) {
            return new EvaUser(in);
        }

        @Override
        public EvaUser[] newArray(int size) {
            return new EvaUser[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Challenge> getCompletedChallenges() {
        return completedChallenges;
    }

    public List<Challenge> getTodaysChallenge() {
        return todaysChallenge;
    }

    public List<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(username);
        dest.writeString((todaysChallenge.get(0).getId()+""));
    }


}
