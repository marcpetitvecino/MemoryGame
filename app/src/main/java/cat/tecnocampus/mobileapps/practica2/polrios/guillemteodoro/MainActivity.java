package cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.archive.ArchiveActivity;
import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.database.AppDatabase;
import cat.tecnocampus.mobileapps.practica2.polrios.guillemteodoro.database.Game;

public class MainActivity extends AppCompatActivity {

    private RecyclerView game;
    private String userName = "N/A";
    private float pairNumber = 24;
    private List<Card> dataSet = new ArrayList<>();
    private GameAdapter gameAdapter;
    private Card lastClickedCard;
    private View lastClickedCardView;
    float cardsRemaining;
    private int score = 0;
    AppDatabase db;
    private int correctPairs = 0;
    private boolean isCompairing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();
        initViews();
        createInputDialog();
    }

    private void initAdapter() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(getResources().getIdentifier("apple", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("avocado", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("bananas", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("burger", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("candy_cane", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("carrot", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("chinese", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("dark_chocolate", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("fruit", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("grapes", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("juniper", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("lollipop", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("mango", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("meat", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("orange", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("pepper", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("pineapple", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("pizza_slice", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("snickers", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("steak", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("strawberry", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("sweet_potato", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("taco", "drawable", getPackageName()));
                imageList.add(getResources().getIdentifier("white_chocolate", "drawable", getPackageName()));


        game.setLayoutManager(new GridLayoutManager(this, 4));
        Collections.shuffle(imageList);
        cardsRemaining = pairNumber-1;
        int random;

        for (int i = 0; i < pairNumber; i++) {
            if (cardsRemaining >= 0) {
                try {
                    random = ThreadLocalRandom.current().nextInt(0, (int) cardsRemaining);
                } catch (IllegalArgumentException e) {
                    random = 0;
                }
                dataSet.add(new Card(true, i+1, imageList.get(random)));
                dataSet.add(new Card(true, i+1, imageList.get(random)));
                imageList.remove(random);
                cardsRemaining--;
            }

        };
        Collections.shuffle(dataSet);
        gameAdapter = new GameAdapter(dataSet, this, this::manageCardClick);
        gameAdapter.notifyDataSetChanged();
        game.setAdapter(gameAdapter);
    }

    private void createInputDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enter your username and the number of pairs you want");
        final EditText inputUsername = new EditText(this);
        inputUsername.setHint("Username");
        final EditText inputCardumber = new EditText(this);
        inputCardumber.setHint("Number of pairs");
        inputCardumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(lp);
        linearLayout.addView(inputUsername);
        linearLayout.addView(inputCardumber);
        dialog.setView(linearLayout);
        dialog.setPositiveButton("Start", (dialog1, which) -> {
            if (!"".equals(inputCardumber.getText().toString())) {
                pairNumber = Math.min(Integer.parseInt(inputCardumber.getText().toString()), 24);
            } else {
                Toast.makeText(this, "The default input is 24", Toast.LENGTH_SHORT).show();
                pairNumber = 24;
            }

            if (0 == (Integer.parseInt((inputCardumber.getText().toString())))) {
                Toast.makeText(this, "The minimum input is 2", Toast.LENGTH_SHORT).show();
                pairNumber = 2;
            }

            userName = inputUsername.getText().toString();
            initAdapter();
        });

        dialog.setCancelable(false);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }

    private void manageCardClick(View v) {
        Card clickedCard = (Card) v.getTag();

        if (!isCompairing) {
            if (lastClickedCard != null) {
                if (clickedCard.isHidden() && !lastClickedCard.isHidden()) {
                    showCards(v);
                    compareCards(clickedCard, v);
                } else {
                    storeLastCard(clickedCard, v);
                }

            } else {
                storeLastCard(clickedCard, v);
            }
        }
    }

    private void storeLastCard(Card clickedCard, View v) {
        lastClickedCard = clickedCard;
        lastClickedCardView = v;
        clickedCard.setHidden(false);
        showCards(v);
    }

    private void compareCards(Card clickedCard, View v) {
        isCompairing = true;
        if (clickedCard.getValue() == lastClickedCard.getValue()) {
            v.postDelayed((Runnable) () -> {
                v.setVisibility(View.GONE);
                lastClickedCardView.setVisibility(View.GONE);
                lastClickedCard = clickedCard;
                lastClickedCard.setHidden(true);
                lastClickedCardView = v;
                correctPairs++;
                score+=20;
                MainActivity.this.setTitle("Score: "+score);
                if (correctPairs == pairNumber) {
                    Game game = new Game(score, userName, (int) pairNumber);
                    db.gameDao().insert(game);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Congratulatons! You won! \n Your score was " + score + " points!");
                    builder.setOnDismissListener(dialog -> {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                isCompairing = false;
            }, 1000);
        } else {
            v.postDelayed((Runnable) () -> {
                hideCards(v);
                hideCards(lastClickedCardView);
                lastClickedCard.setHidden(true);
                clickedCard.setHidden(true);
                lastClickedCard = clickedCard;
                lastClickedCard.setHidden(true);
                lastClickedCardView = v;
                score-=5;
                MainActivity.this.setTitle("Score: "+score);
                isCompairing = false;
            }, 1000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.archive:
                goToArchiveActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToArchiveActivity() {
        Intent intent = new Intent(this, ArchiveActivity.class);
        startActivity(intent);
    }

    private void showCards(View view) {
        view.findViewById(R.id.card_shown).setVisibility(View.VISIBLE);
        view.findViewById(R.id.card_hidden).setVisibility(View.GONE);
    }

    private void hideCards(View view) {
        view.findViewById(R.id.card_shown).setVisibility(View.GONE);
        view.findViewById(R.id.card_hidden).setVisibility(View.VISIBLE);
    }

    private void initViews() {
        MainActivity.this.setTitle("Score: "+score);
        game = findViewById(R.id.game_container);
    }
}