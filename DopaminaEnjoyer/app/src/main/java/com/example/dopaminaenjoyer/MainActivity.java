package com.example.dopaminaenjoyer;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.media.AudioAttributes;
import android.media.SoundPool;


public class MainActivity extends AppCompatActivity {

    private FrameLayout effectsContainer;
    private Random random = new Random();
    private SoundPool soundPool;
    private int soundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSoundPool();
        effectsContainer = findViewById(R.id.effectsContainer);
        findViewById(R.id.fullscreenButton).setOnClickListener(v -> {
            triggerDopamineEffect();
            playSound();
        });
    }

    private void initSoundPool() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            // Versión antigua (deprecated, pero funciona en APIs antiguas)
            soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }

        // Cargar el sonido desde res/raw
        soundId = soundPool.load(this, R.raw.boom, 1); // ← ¡cambia "pop_sound" por tu nombre de archivo!
    }
    private void playSound() {
        // Reproducir solo si ya se cargó (opcional: podrías usar un listener para mayor precisión)
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }


    private void triggerDopamineEffect() {
        // 1. Mostrar un número grande en amarillo
        showBigYellowNumber();

        // 2. Simular "estallido" con varias partículas
        createExplosionEffect();
    }


    private void showBigYellowNumber() {
        TextView numberView = new TextView(this);
        int randomNumber = random.nextInt(1000) + 1; // Número entre 1 y 1000
        numberView.setText(String.valueOf(randomNumber));
        numberView.setTextColor(Color.YELLOW);
        numberView.setTextSize(80); // Muy grande
        numberView.setTypeface(Typeface.DEFAULT_BOLD);


        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = random.nextInt(effectsContainer.getWidth() - 300);
        params.topMargin = random.nextInt(effectsContainer.getHeight() - 300);
        numberView.setLayoutParams(params);

        effectsContainer.addView(numberView);

        // Animación: aparece grande y se desvanece
        numberView.setAlpha(0f);
        numberView.setScaleX(0.2f);
        numberView.setScaleY(0.2f);

        numberView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(300)
                .withEndAction(() -> {
                    numberView.animate()
                            .alpha(0f)
                            .setDuration(1000)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    effectsContainer.removeView(numberView);
                                }
                                @Override public void onAnimationStart(Animator animation) {}
                                @Override public void onAnimationCancel(Animator animation) {}
                                @Override public void onAnimationRepeat(Animator animation) {}
                            });
                });
    }

    private void createExplosionEffect() {
        int particleCount = 20;
        for (int i = 0; i < particleCount; i++) {
            View particle = new View(this);
            particle.setBackgroundColor(Color.rgb(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
            ));
            FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(20, 20);
            p.leftMargin = effectsContainer.getWidth() / 2 - 10;
            p.topMargin = effectsContainer.getHeight() / 2 - 10;
            particle.setLayoutParams(p);
            effectsContainer.addView(particle);

            // Animación de explosión
            float endX = (random.nextFloat() - 0.5f) * 800;
            float endY = (random.nextFloat() - 0.5f) * 800;

            ObjectAnimator animX = ObjectAnimator.ofFloat(particle, "translationX", 0, endX);
            ObjectAnimator animY = ObjectAnimator.ofFloat(particle, "translationY", 0, endY);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(particle, "alpha", 1f, 0f);

            ValueAnimator scale = ValueAnimator.ofFloat(1f, 3f);
            scale.addUpdateListener(animator -> {
                float s = (Float) animator.getAnimatedValue();
                particle.setScaleX(s);
                particle.setScaleY(s);
            });

            animX.setDuration(800);
            animY.setDuration(800);
            alpha.setDuration(800);
            scale.setDuration(800);

            animX.start();
            animY.start();
            alpha.start();
            scale.start();

            // Eliminar vista tras animación
            animX.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    effectsContainer.removeView(particle);
                }
                @Override public void onAnimationStart(Animator animation) {}
                @Override public void onAnimationCancel(Animator animation) {}
                @Override public void onAnimationRepeat(Animator animation) {}
            });
        }
    }

}
