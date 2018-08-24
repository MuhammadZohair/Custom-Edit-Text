package byteitsolutions.co.customedittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tooltip.Tooltip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

/**
 * Project Title: Custom Edit text
 * Created on: 01/08/2018
 * Created by: Muhammad Zohair
 * Description: This project implements custom edittext with icon and separator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* Regex used for validating email address*/
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /* Regex used for validating password*/
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);


    private EditText emailEditText; // email field
    private EditText passwordEditText; // password field
    private ImageView nextButton; // button
    private ImageView usernameErrorImageView, passwordErrorImageView;

    public static boolean validateEmail(CharSequence emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePassword(CharSequence passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.find();
    }

    /**
     * This method is called as soon as the activity is created
     *
     * @param savedInstanceState used to transfer data between activities
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWidgets();
    }

    /**
     * This method initializes the widgets used in the current activity
     */
    private void initializeWidgets() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        /*
         * TextChange listener checks the input on realtime inside the edit text
         * */
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernameErrorImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    usernameErrorImageView.setVisibility(View.INVISIBLE);
                }
                if (validateEmail(charSequence)) {
                    usernameErrorImageView.setImageDrawable(getResources().getDrawable(R.drawable.correct));
                    usernameErrorImageView.setClickable(false);
                } else {
                    usernameErrorImageView.setImageDrawable(getResources().getDrawable(R.drawable.error));
                    usernameErrorImageView.setClickable(true);
                }
            }

            // not required
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        /*
         * TextChange listener checks the input on realtime inside the edit text
         * */
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordErrorImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    passwordErrorImageView.setVisibility(View.INVISIBLE);
                }
                if (validatePassword(charSequence)) {
                    passwordErrorImageView.setImageDrawable(getResources().getDrawable(R.drawable.correct));
                    passwordErrorImageView.setClickable(false);
                } else {
                    passwordErrorImageView.setImageDrawable(getResources().getDrawable(R.drawable.error));
                    passwordErrorImageView.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        /* Checkbox implements the functionality of showing and hiding the password, if the check box
         * is selected the password is show if it is not selected then the passsword is invisible */
        CheckBox showPasswordCheckBox = findViewById(R.id.showPasswordCheckBox);
        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                else
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        usernameErrorImageView = findViewById(R.id.usernameErrorImageView);
        usernameErrorImageView.setOnClickListener(this);
        usernameErrorImageView.setVisibility(View.INVISIBLE); // set the error icon to invisible

        passwordErrorImageView = findViewById(R.id.passwordErrorImageView);
        passwordErrorImageView.setOnClickListener(this);
        passwordErrorImageView.setVisibility(View.INVISIBLE); // set the error icon to invisible

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this); // setting click listener to the imageview
    }

    /**
     * @param view
     */
    @Override
    public void onClick(View view) {

        if (view == usernameErrorImageView) {
            // tooltip opens when the error icon is clicked
            new Tooltip.Builder(view)
                    .setDismissOnClick(true)
                    .setCornerRadius((float) 50)
                    .setArrowHeight((float) 50)
                    .setArrowWidth((float) 70)
                    .setPadding((float) 50)
                    .setTextSize((float) 18)
                    .setBackgroundColor(getResources().getColor(R.color.primaryColor))
                    .setTextColor(getResources().getColor(R.color.colorAccent))
                    .setText("* At least 8 chars\n" +
                            "* should have @ symbol \n" +
                            "* ends with atleast 2 chars\n" +
                            "* Should not contain space or tabs.")
                    .setCancelable(true)
                    .show();
        }

        if (view == passwordErrorImageView) {
            // tooltip opens when the error icon is clicked
            new Tooltip.Builder(view)
                    .setDismissOnClick(true)
                    .setCornerRadius((float) 50)
                    .setArrowHeight((float) 50)
                    .setArrowWidth((float) 70)
                    .setPadding((float) 50)
                    .setTextSize((float) 18)
                    .setBackgroundColor(getResources().getColor(R.color.primaryColor))
                    .setTextColor(getResources().getColor(R.color.colorAccent))
                    .setText("* At least 8 chars\n" +
                            "* At least one digit\n" +
                            "* At least one lower case letter\n" +
                            "* At least one upper case letter\n" +
                            "* At least one special character\n" +
                            "* Should not contain space or tabs.")
                    .setCancelable(true)
                    .show();

        }

        /* If next Button is clicked this block of code will execute */
        if (view == nextButton) {
            String email = emailEditText.getText().toString().trim(); // getting string inside the edit text and removing whitespaces
            String password = passwordEditText.getText().toString().trim();

            /* Checks if the fields are empty or not, and prompt the user accordingly */
            if (validateEmail(emailEditText.getText().toString().trim())
                    && validatePassword(passwordEditText.getText().toString().trim())) {
                Toasty.success(getApplicationContext(), "Success!", Toast.LENGTH_SHORT, true).show();
                emailEditText.setText(null);
                passwordEditText.setText(null);
                usernameErrorImageView.setVisibility(View.INVISIBLE);
                passwordErrorImageView.setVisibility(View.INVISIBLE);
            } else
                /*Toasty is a custom API that makes normal toasts more interesting*/
                Toasty.error(getApplicationContext(), "Email or Password invalid", Toast.LENGTH_SHORT, true).show();
        }
    }

}
