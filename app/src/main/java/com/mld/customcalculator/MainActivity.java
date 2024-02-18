package com.mld.customcalculator;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int HISTORY_REQUEST_CODE = 0;
    //Declaration
    EditText display;
    Button clearBtn, divBtn, btnSeven, btnEight, btnNine, btnMul, btnFour, btnFive, btnSix, btnSub, btnOne, btnTwo, btnThree, btnAdd, btnDot, btnZero, BtnBract, btnEqual;
    ImageButton backspace;
    List<String> historyItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Initialization
        //Connecting the Button Object with respective button of xml using its id
        display = findViewById(R.id.display);
        clearBtn = findViewById(R.id.clearbtn);
        backspace = findViewById(R.id.backspacebtn);
        divBtn = findViewById(R.id.divbtn);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnMul = findViewById(R.id.btnMul);
        btnFour = findViewById(R.id.btnfour);
        btnFive = findViewById(R.id.btnfive);
        btnSix = findViewById(R.id.btnSix);
        btnSub = findViewById(R.id.btnSub);
        btnThree = findViewById(R.id.buttonthree);
        btnTwo = findViewById(R.id.btnTwo);
        btnOne = findViewById(R.id.btnone);
        btnAdd = findViewById(R.id.btnAdd);
        btnDot = findViewById(R.id.btnDot);
        btnZero = findViewById(R.id.btnZero);
        BtnBract = findViewById(R.id.BtnBract);
        btnEqual = findViewById(R.id.btneql);

        historyItems = new ArrayList<>();
        // Setting OnClickListener for all buttons
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String currentText = display.getText().toString();
                    if (!currentText.isEmpty()) {
                        display.setText(currentText.substring(0, currentText.length() - 1));
                    }
                } catch (Exception e) {
                    // Handle exceptions here
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            backspace.setImageResource(R.drawable.backspace);
        } else {
            backspace.setImageResource(R.drawable.lightbackspace);
        }


        divBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("/");
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("7");
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("8");
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("9");
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("*");
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("4");
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("5");
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("6");
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("-");
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("3");
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("2");
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("1");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("+");
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay(".");
            }
        });

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDisplay("0");
            }
        });


        BtnBract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String exp = display.getText().toString();
                    if (exp.isEmpty() || isOperatorIsLastChar(exp)) {
                        // If the expression is empty or the last character is an operator,
                        // append an opening bracket '('
                        appendToDisplay("(");
                    } else {
                        // Check the count of open and closed brackets
                        int openBracketsCount = countOccurrences(exp, '(');
                        int closedBracketsCount = countOccurrences(exp, ')');

                        if (openBracketsCount > closedBracketsCount) {
                            // Add a closing bracket ')'
                            appendToDisplay(")");
                        } else {
                            // Add an opening bracket '('
                            appendToDisplay("(");
                        }
                    }
                } catch (Exception e) {
                    // Handle exceptions here
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            private int countOccurrences(String str, char ch) {
                int count = 0;
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == ch) {
                        count++;
                    }
                }
                return count;
            }

            private boolean isOperatorIsLastChar(String exp) {
                if (exp.isEmpty()) {
                    return false;
                } else {
                    char lastChar = exp.charAt(exp.length() - 1);
                    return lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/';
                }
            }
        });


        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String exp = display.getText().toString();
                    String result = CalculationHelper.evaluateExpression(exp);
                    display.setText(result);
                    historyItems.add(exp + " = " + result);
                } catch (Exception e) {
                    // Handle exceptions here
                    Toast.makeText(MainActivity.this, "Error: " + e.getMessage()+"/nBad Expression", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to append text to display
    private void appendToDisplay(String text) {
        display.append(text);
    }

    // Method to evaluate the expression
    private void evaluateExpression(String expression) {
        try {
            String result = CalculationHelper.evaluateExpression(expression);
            display.setText(String.valueOf(result));
        } catch (IllegalArgumentException e) {
            display.setText("Error");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            if (item.getItemId() == R.id.action_history) {

                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                intent.putStringArrayListExtra("history", (ArrayList<String>) historyItems);
                startActivityForResult(intent, HISTORY_REQUEST_CODE);
                return true;
            } else if (item.getItemId() == R.id.action_thanks) {
                Toast.makeText(this, "Thank You", Toast.LENGTH_SHORT).show();
                return true;

            } else {
                return super.onOptionsItemSelected(item);
            }
        } catch (Exception e) {
            // Handle exceptions here
            Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == HISTORY_REQUEST_CODE && resultCode == RESULT_OK) {
                if (data.getStringExtra("selected_expression")!=null) {
                    String selectedExpression = data.getStringExtra("selected_expression");
                    // Setting the selected expression in display
                    // since its stored there as a+b = y we use substring to reduce it till =
                    display.setText(selectedExpression.substring(0, selectedExpression.indexOf("=")));
                }else{
                    historyItems.clear();
                }
            }
        } catch (Exception e) {
            // Handle exceptions here
            Toast.makeText(MainActivity.this, "Bad Expression: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
