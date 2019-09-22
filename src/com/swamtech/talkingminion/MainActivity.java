package com.swamtech.talkingminion;

import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.engine.Engine;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.LayoutGameActivity;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePack;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackLoader;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackTextureRegionLibrary;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.TexturePackerTextureRegion;
import org.andengine.extension.texturepacker.opengl.texture.util.texturepacker.exception.TexturePackParseException;

import com.flurry.android.FlurryAdListener;
import com.flurry.android.FlurryAdSize;
import com.flurry.android.FlurryAdType;
import com.flurry.android.FlurryAds;
import com.flurry.android.FlurryAgent;

import android.content.res.Configuration;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends LayoutGameActivity implements
		IAccelerationListener, FlurryAdListener {
	private static final int CAMERA_WIDTH = 320;
	private static final int CAMERA_HEIGHT = 500;
	private Scene m_splashscene;
	private Camera m_Camera;
	private Scene m_Scene;
    private Scene m_newScene;
	private Entity splashAnimationEntity;
	private Entity idleMinionEntity;
	private Entity laughMinionEntity;
	private Entity hornMinionEntity;
	private Entity shakeMinionRightEntity;
	private Entity shakeMinionLeftEntity;
	private Entity speakMinionEntity;
	private Entity hitMinionEntity;
	private Entity fireMinionEntity;
	private Entity mirrorBrokeEntity;
	private AnimatedSprite splashAnimation0;
	private AnimatedSprite splashAnimation1;
	private AnimatedSprite splashAnimation2;
	private AnimatedSprite splashAnimation3;
	private AnimatedSprite splashAnimation4;
	private AnimatedSprite splashAnimation5;
	private AnimatedSprite splashAnimation6;
	private AnimatedSprite splashAnimation7;
	private AnimatedSprite splashAnimation8;
	private AnimatedSprite splashAnimation9;
	private AnimatedSprite idleMinion0;
	private AnimatedSprite idleMinion1;
	private AnimatedSprite idleMinion2;
	private AnimatedSprite idleMinion3;
	private AnimatedSprite idleMinion4;
	private AnimatedSprite idleMinion5;
	private AnimatedSprite idleMinion6;
	private AnimatedSprite idleMinion7;
	private AnimatedSprite idleMinion8;
	private AnimatedSprite idleMinion9;
	private AnimatedSprite idleMinion10;

	private AnimatedSprite minionLaugh0;
	private AnimatedSprite minionLaugh1;
	private AnimatedSprite minionLaugh3;
	private AnimatedSprite minionLaugh4;
	private AnimatedSprite minionLaugh5;
	private AnimatedSprite minionLaugh6;
	private AnimatedSprite minionLaugh7;

	private AnimatedSprite minionHorn0;
	private AnimatedSprite minionHorn1;
	private AnimatedSprite minionHorn2;
	private AnimatedSprite minionHorn3;
	private AnimatedSprite minionHorn4;
	private AnimatedSprite minionHorn5;

	private AnimatedSprite speakMinion0;
	private AnimatedSprite speakMinion1;
	private AnimatedSprite speakMinion2;

	private AnimatedSprite shakeMinionRight0;
	private AnimatedSprite shakeMinionRight1;
	private AnimatedSprite shakeMinionRight2;
	private AnimatedSprite shakeMinionRight3;

	private AnimatedSprite shakeMinionLeft0;
	private AnimatedSprite shakeMinionLeft1;
	private AnimatedSprite shakeMinionLeft2;
	private AnimatedSprite shakeMinionLeft3;

	private AnimatedSprite fireMinion0;
	private AnimatedSprite fireMinion1;

	// private AnimatedSprite mirrorBroke0;
	// private AnimatedSprite mirrorBroke1;
	private AnimatedSprite mirrorBroke2;
	private AnimatedSprite mirrorBroke3;
	private AnimatedSprite mirrorBroke4;

	private AnimatedSprite hitMinion;
	private ITextureRegion mBackgroundTextureRegion;
	private ITextureRegion mLaughMinion;
	private ITextureRegion mMinionHorn;
	private ITextureRegion mMinionFire;
	private ITextureRegion mMirrorBroken;
	private BitmapTextureAtlas texBackground;
	private BitmapTextureAtlas textlaughingButton;
	private BitmapTextureAtlas textureHornButton;
	private BitmapTextureAtlas textFireMinionButton;
	private BitmapTextureAtlas textMirrorBrrokenButton;
	private long lastUpdate = -1;
	private float x, y, z;
	private float last_x, last_y, last_z;
	private static final int SHAKE_THRESHOLD = 800;
	long lastShakeTime = 0;
	float lastShakeX, lastShakeY, lastShakeZ = 0;

	private static boolean isAnimating;
	private static boolean isLaughing;
	private static boolean isPlayingHorn;
	private static boolean isMinionFrightened;
	private static boolean isMinionHitted;
	private static boolean isMirrorBroken;
	private static boolean isMinionFired;
	private static boolean isGameExisted;
    private static boolean isGameCreated = false;
	private static boolean isBackKeyPressed;
	private static boolean isHomeKeyPressed;
	Sound mMinionKiss;
	Sound mMinionLaugh;
	Sound mMinionHornSound;
	Sound mMinionFrightenedSound;
	// Sound mMinionHelloSound;
	Sound mMinionHitSound;
	Sound mMirrorBrokenSound;
	Sound mMinionScreamingSound;
	Sound mRPGSound;
	ButtonSprite minionHornButton;
	ButtonSprite fireMinionButton;
	ButtonSprite mirrorBroke;
	ButtonSprite minionLaughButton;
	// Sound mIdleMinionSound;
	private static boolean isListening;
	private static final int SAMPLE_RATE_IN_HZ = 8000;
	private static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
	private static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
	private static final int AUDIO_SOURCE = MediaRecorder.AudioSource.MIC;
	private static final String TAG = "VoiceDetection";
	private static final int START_RECORD_FROM = 250;
	private static final int CHECK_BLOCK_COUNT = 3;
	AudioRecord record = null;
	AudioTrack track = null;
	boolean isPlaying;

	private AudioRecord audioRecorder = null;
	private int minBufferSizeInBytes = 0;

	private int maxJitter;
	FrameLayout mBanner;
	ImageView splashImage;
	private String mAdSpaceName = "talkingminionbannerr";
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Log.d(TAG, "on Create Scene Create Create Create   Talking Minion() called");
	        splashImage = (ImageView)findViewById(R.id.imageView1);
	        Display display = getWindowManager().getDefaultDisplay();
	        int width = display.getWidth();
	        int height = display.getHeight();
	        RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);
	        splashImage.setLayoutParams(parms);
	        isBackKeyPressed = false;
	        isHomeKeyPressed = false;
	        mBanner = (FrameLayout) findViewById(R.id.banner);
			FlurryAgent.onStartSession(this, "XKFHFC5H5WCH4YCFTHRR");
			 //FlurryAds.enableTestAds(true);
			// get callbacks for ad events
			FlurryAds.setAdListener(this);
			// fetch and prepare ad for this ad space. won’t render one yet
			if (!FlurryAds.isAdReady(mAdSpaceName))
				FlurryAds.fetchAd(this, mAdSpaceName, mBanner,
						FlurryAdSize.BANNER_BOTTOM);
	     
	 }
	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		// Create a fixed step engine updating at 60 steps per second
		super.onCreateEngine(pEngineOptions);
		return new FixedStepEngine(pEngineOptions, 60);
	}

	@Override
	public EngineOptions onCreateEngineOptions() {

		  Log.d(TAG, "on CreateEngineOptionsCreateEngineOptionsCreateEngineOptions  Talking Minion() called");
		m_Camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions en = new EngineOptions(true,
				ScreenOrientation.PORTRAIT_FIXED, new FillResolutionPolicy(),
				m_Camera);
		en.getAudioOptions().setNeedsMusic(true);
		en.getAudioOptions().setNeedsSound(true);
        en.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return en;
	}
	@Override
	public void onReloadResources() {
		super.onReloadResources();
	
	
	}
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		  Log.d(TAG, "on Create Resources Create ResourcesCreate ResourcesCreate Resources  Talking Minion() called");
		SoundFactory.setAssetBasePath("gfx/");
		MusicFactory.setAssetBasePath("gfx/");
		try {
			mMinionKiss = SoundFactory.createSoundFromAsset(getSoundManager(),
					this, "sound/minion_kiss.mp3");

		} catch (Exception e) {
			e.printStackTrace();
		}
		mMinionKiss.setVolume(0.5f);
		splashAnimation9 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"9,10splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		splashAnimation8 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"8,9splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		splashAnimation7 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"7,8splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		splashAnimation6 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"6,7splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		splashAnimation5 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"5,6splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		splashAnimation4 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"4,5splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		splashAnimation3 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"3,4splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		splashAnimation2 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"2,3splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		splashAnimation1 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"1,2splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		splashAnimation0 = new AnimatedSprite(5, -55, getTiledTextureFromPack(
				"0,1splashanimation").deepCopy(),
				mEngine.getVertexBufferObjectManager());
		pOnCreateResourcesCallback.onCreateResourcesFinished();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		  Log.d(TAG, "on Create Scene Create SceneCreate SceneCreate Scene  Talking Minion() called");
		m_splashscene = new Scene();
		initSplashScene();
		pOnCreateSceneCallback.onCreateSceneFinished(m_splashscene);

	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResumeResumeResumeResumeResumeResumeResumeResumeResume  Talking Minion() called");
		
         
	}
	@Override
   protected void onPause() {
		super.onPause();
		Log.d(TAG, "Simple Simple onPausePausePausePausePausePausePausePausePausePausePausePause  Talking Minion() called");
		if(!isBackKeyPressed && !isHomeKeyPressed) {
		splashImage.setVisibility(View.VISIBLE); 
		}
	};
	@Override
	public void onPauseGame() {
		if(!isGameCreated) {
			return;
		}
		super.onPauseGame();
		
		Log.d(TAG, "onPausePausePausePausePausePausePausePausePausePausePausePause  Talking Minion() called");
		
		// FlurryAgent.onEndSession(this);
	
		if (mMinionLaugh != null && mMinionLaugh.isLoaded()) {
			mMinionLaugh.stop();
		}
		if (mMinionFrightenedSound != null && mMinionFrightenedSound.isLoaded()) {
			mMinionFrightenedSound.stop();
		}
		if (mMinionHitSound != null && mMinionHitSound.isLoaded()) {
			mMinionHitSound.stop();
		}
		if (mMinionHornSound != null && mMinionHornSound.isLoaded()) {
			mMinionHornSound.stop();
		}

		if (mMinionScreamingSound != null && mMinionScreamingSound.isLoaded()) {
			mMinionScreamingSound.stop();
		}
		if (mMirrorBrokenSound != null && mMirrorBrokenSound.isLoaded()) {
			mMirrorBrokenSound.stop();
		}
		if (mRPGSound != null && mRPGSound.isLoaded()) {
			mRPGSound.stop();
		}

		
		 if (this.mEngine != null && this.mEngine.isRunning()) {
			 	mEngine.stop();
		 }	 
		 
	}
	@Override
    public synchronized void onResumeGame() {  
		  super.onResumeGame();
		  Log.d(TAG, "onResumeResumeResumeResumeResumeResumeResumeResumeResume Game  Talking Minion() called");
		  this.mEngine.start();

	}
	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		  Log.d(TAG, "on Populate Scene Populate Scene  Talking Minion() called");
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				loadResources();
				initBackground();
				loadScenes();

				splashAnimationEntity.detachSelf();
				mEngine.setScene(m_Scene);
				// animateIdleSprite();

				return null;

			}
		}.execute();
		pOnPopulateSceneCallback.onPopulateSceneFinished();

	}

	@Override
	public void onGameCreated() {
		super.onGameCreated();
		  Log.d(TAG, "on Game created  Game created  Game created  Game created  Talking Minion() called");
		  isGameCreated = true;
		  
	}
	
	@Override
	 protected void onDestroy() {
		super.onDestroy();
		m_Scene.dispose();
	
	
	};
	@Override
	public void onDestroyResources() throws Exception {
		 super.onDestroyResources();
		 if (mMinionLaugh != null && mMinionLaugh.isLoaded()) {
				mMinionLaugh.release();
			}
			/*
			 * if(mIdleMinionSound != null && mIdleMinionSound.isLoaded()) {
			 * mIdleMinionSound.release(); }
			 */
			if (mMinionFrightenedSound != null && mMinionFrightenedSound.isLoaded()) {
				mMinionFrightenedSound.release();
			}
			if (mMinionHitSound != null && mMinionHitSound.isLoaded()) {
				mMinionHitSound.release();
			}
			if (mMinionHornSound != null && mMinionHornSound.isLoaded()) {
				mMinionHornSound.release();
			}
			/*
			 * if(mMinionHelloSound!=null && mMinionHelloSound.isLoaded()) {
			 * mMinionHelloSound.release(); }
			 */
			if (mMinionScreamingSound != null && mMinionScreamingSound.isLoaded()) {
				mMinionScreamingSound.release();
			}
			if (mMirrorBrokenSound != null && mMirrorBrokenSound.isLoaded()) {
				mMirrorBrokenSound.release();
			}
			if (mRPGSound != null && mRPGSound.isLoaded()) {
				mRPGSound.release();
			}
	 };
	@Override
	public void onGameDestroyed() {
		super.onGameDestroyed();
		isGameExisted = true;
	
		if (this.isGameLoaded()) {
			audioRecorder.stop();
			audioRecorder.release();
			audioRecorder = null;
			track.release();

		}
		finish();
		System.exit(0);

	}

	@Override
	public void onConfigurationChanged(Configuration pNewConfig) {
		Log.d(TAG, "onConfigurationChanged() called");
		// Insert code to analyze the new config and make changes to your
		// application
		super.onConfigurationChanged(pNewConfig);
	}

	public TiledTextureRegion getTiledTextureFromPack(String name) {
		TexturePackTextureRegionLibrary packer = null;
		TexturePack spritesheetTexturePack = null;
		try {
			spritesheetTexturePack = new TexturePackLoader(getTextureManager(),
					"").loadFromAsset(getAssets(), name + ".xml");
			spritesheetTexturePack.loadTexture();
			packer = spritesheetTexturePack
					.getTexturePackTextureRegionLibrary();
		} catch (final TexturePackParseException e) {
			// Debug.e(e);
		}
		Log.d("sizee pai", Integer.toString(packer.getIDMapping().size()));
		TexturePackerTextureRegion[] obj = new TexturePackerTextureRegion[packer
				.getIDMapping().size()];

		for (int i = 0; i < packer.getIDMapping().size(); i++) {
			obj[i] = packer.get(i);
		}

		TiledTextureRegion texture = new TiledTextureRegion(
				spritesheetTexturePack.getTexture(), obj);

		return texture;
	}
    
	private void initSplashScene() {
		splashAnimationEntity = new Entity();
		splashAnimationEntity.attachChild(splashAnimation9);
		splashAnimationEntity.attachChild(splashAnimation8);
		splashAnimationEntity.attachChild(splashAnimation7);
		splashAnimationEntity.attachChild(splashAnimation6);
		splashAnimationEntity.attachChild(splashAnimation5);
		splashAnimationEntity.attachChild(splashAnimation4);
		splashAnimationEntity.attachChild(splashAnimation3);
		splashAnimationEntity.attachChild(splashAnimation2);
		splashAnimationEntity.attachChild(splashAnimation1);
		splashAnimationEntity.attachChild(splashAnimation0);
		splashAnimation0.setVisible(false);
		splashAnimation1.setVisible(false);
		splashAnimation2.setVisible(false);
		splashAnimation3.setVisible(false);
		splashAnimation4.setVisible(false);
		splashAnimation5.setVisible(false);
		splashAnimation6.setVisible(false);
		splashAnimation7.setVisible(false);
		splashAnimation8.setVisible(false);
		splashAnimation9.setVisible(true);
		splashAnimationEntity.setVisible(true);
		// splashAnimationEntity.setCullingEnabled(true);
		m_splashscene.attachChild(splashAnimationEntity);
		mEngine.registerUpdateHandler(new TimerHandler(1f,
				new ITimerCallback() {
					public void onTimePassed(final TimerHandler pTimerHandler) {

						mEngine.unregisterUpdateHandler(pTimerHandler);
						MainActivity.this.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								animateSplashScreen();
							}
						});

					}
				}));

	}
    
	public void animateSplashScreen() {

		splashAnimation9.animate(150, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				splashAnimation0.setVisible(false);
				splashAnimation1.setVisible(false);
				splashAnimation2.setVisible(false);
				splashAnimation3.setVisible(false);
				splashAnimation4.setVisible(false);
				splashAnimation5.setVisible(false);
				splashAnimation6.setVisible(false);
				splashAnimation7.setVisible(false);
				splashAnimation9.setVisible(false);
				splashAnimation8.setVisible(true);
				// if(mMinionKiss.isLoaded()) {
				mMinionKiss.stop();
				mMinionKiss.play();
				// }
				splashAnimation8.animate(150, 0, new IAnimationListener() {

					@Override
					public void onAnimationStarted(
							AnimatedSprite pAnimatedSprite,
							int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationLoopFinished(
							AnimatedSprite pAnimatedSprite,
							int pRemainingLoopCount, int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFrameChanged(
							AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
							int pNewFrameIndex) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// TODO Auto-generated method stub
						splashAnimation0.setVisible(false);
						splashAnimation1.setVisible(false);
						splashAnimation2.setVisible(false);
						splashAnimation3.setVisible(false);
						splashAnimation4.setVisible(false);
						splashAnimation5.setVisible(false);
						splashAnimation6.setVisible(false);
						splashAnimation8.setVisible(false);
						splashAnimation9.setVisible(false);
						splashAnimation7.setVisible(true);
						splashAnimation7.animate(150, 0,
								new IAnimationListener() {

									@Override
									public void onAnimationStarted(
											AnimatedSprite pAnimatedSprite,
											int pInitialLoopCount) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationLoopFinished(
											AnimatedSprite pAnimatedSprite,
											int pRemainingLoopCount,
											int pInitialLoopCount) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationFrameChanged(
											AnimatedSprite pAnimatedSprite,
											int pOldFrameIndex,
											int pNewFrameIndex) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationFinished(
											AnimatedSprite pAnimatedSprite) {
										// ////////////////////////////////////////////////
										// TODO Auto-generated method stub
										splashAnimation0.setVisible(false);
										splashAnimation1.setVisible(false);
										splashAnimation2.setVisible(false);
										splashAnimation3.setVisible(false);
										splashAnimation4.setVisible(false);
										splashAnimation5.setVisible(false);
										splashAnimation7.setVisible(false);
										splashAnimation8.setVisible(false);
										splashAnimation9.setVisible(false);
										splashAnimation6.setVisible(true);
										splashAnimation6.animate(150, 0,
												new IAnimationListener() {

													@Override
													public void onAnimationStarted(
															AnimatedSprite pAnimatedSprite,
															int pInitialLoopCount) {
														// TODO Auto-generated
														// method stub

													}

													@Override
													public void onAnimationLoopFinished(
															AnimatedSprite pAnimatedSprite,
															int pRemainingLoopCount,
															int pInitialLoopCount) {
														// TODO Auto-generated
														// method stub

													}

													@Override
													public void onAnimationFrameChanged(
															AnimatedSprite pAnimatedSprite,
															int pOldFrameIndex,
															int pNewFrameIndex) {
														// TODO Auto-generated
														// method stub

													}

													@Override
													public void onAnimationFinished(
															AnimatedSprite pAnimatedSprite) {
														// TODO Auto-generated
														// method stub
														splashAnimation0
																.setVisible(false);
														splashAnimation1
																.setVisible(false);
														splashAnimation2
																.setVisible(false);
														splashAnimation3
																.setVisible(false);
														splashAnimation4
																.setVisible(false);
														splashAnimation6
																.setVisible(false);
														splashAnimation7
																.setVisible(false);
														splashAnimation8
																.setVisible(false);
														splashAnimation9
																.setVisible(false);
														splashAnimation5
																.setVisible(true);
														splashAnimation5
																.animate(
																		150,
																		0,
																		new IAnimationListener() {

																			@Override
																			public void onAnimationStarted(
																					AnimatedSprite pAnimatedSprite,
																					int pInitialLoopCount) {
																				// TODO
																				// Auto-generated
																				// method
																				// stub

																			}

																			@Override
																			public void onAnimationLoopFinished(
																					AnimatedSprite pAnimatedSprite,
																					int pRemainingLoopCount,
																					int pInitialLoopCount) {
																				// TODO
																				// Auto-generated
																				// method
																				// stub

																			}

																			@Override
																			public void onAnimationFrameChanged(
																					AnimatedSprite pAnimatedSprite,
																					int pOldFrameIndex,
																					int pNewFrameIndex) {
																				// TODO
																				// Auto-generated
																				// method
																				// stub

																			}

																			@Override
																			public void onAnimationFinished(
																					AnimatedSprite pAnimatedSprite) {
																				// TODO
																				// Auto-generated
																				// method
																				// stub
																				splashAnimation0
																						.setVisible(false);
																				splashAnimation1
																						.setVisible(false);
																				splashAnimation2
																						.setVisible(false);
																				splashAnimation3
																						.setVisible(false);
																				splashAnimation5
																						.setVisible(false);
																				splashAnimation6
																						.setVisible(false);
																				splashAnimation7
																						.setVisible(false);
																				splashAnimation8
																						.setVisible(false);
																				splashAnimation9
																						.setVisible(false);
																				splashAnimation4
																						.setVisible(true);
																				splashAnimation4
																						.animate(
																								150,
																								0,
																								new IAnimationListener() {

																									@Override
																									public void onAnimationStarted(
																											AnimatedSprite pAnimatedSprite,
																											int pInitialLoopCount) {
																										// TODO
																										// Auto-generated
																										// method
																										// stub

																									}

																									@Override
																									public void onAnimationLoopFinished(
																											AnimatedSprite pAnimatedSprite,
																											int pRemainingLoopCount,
																											int pInitialLoopCount) {
																										// TODO
																										// Auto-generated
																										// method
																										// stub

																									}

																									@Override
																									public void onAnimationFrameChanged(
																											AnimatedSprite pAnimatedSprite,
																											int pOldFrameIndex,
																											int pNewFrameIndex) {
																										// TODO
																										// Auto-generated
																										// method
																										// stub

																									}

																									@Override
																									public void onAnimationFinished(
																											AnimatedSprite pAnimatedSprite) {
																										// TODO
																										// Auto-generated
																										// method
																										// stub
																										splashAnimation0
																												.setVisible(false);
																										splashAnimation1
																												.setVisible(false);
																										splashAnimation2
																												.setVisible(false);
																										splashAnimation4
																												.setVisible(false);
																										splashAnimation5
																												.setVisible(false);
																										splashAnimation6
																												.setVisible(false);
																										splashAnimation7
																												.setVisible(false);
																										splashAnimation8
																												.setVisible(false);
																										splashAnimation9
																												.setVisible(false);
																										splashAnimation3
																												.setVisible(true);
																										splashAnimation3
																												.animate(
																														150,
																														0,
																														new IAnimationListener() {

																															@Override
																															public void onAnimationStarted(
																																	AnimatedSprite pAnimatedSprite,
																																	int pInitialLoopCount) {
																																// TODO
																																// Auto-generated
																																// method
																																// stub

																															}

																															@Override
																															public void onAnimationLoopFinished(
																																	AnimatedSprite pAnimatedSprite,
																																	int pRemainingLoopCount,
																																	int pInitialLoopCount) {
																																// TODO
																																// Auto-generated
																																// method
																																// stub

																															}

																															@Override
																															public void onAnimationFrameChanged(
																																	AnimatedSprite pAnimatedSprite,
																																	int pOldFrameIndex,
																																	int pNewFrameIndex) {
																																// TODO
																																// Auto-generated
																																// method
																																// stub

																															}

																															@Override
																															public void onAnimationFinished(
																																	AnimatedSprite pAnimatedSprite) {
																																// TODO
																																// Auto-generated
																																// method
																																// stub
																																splashAnimation0
																																		.setVisible(false);
																																splashAnimation1
																																		.setVisible(false);
																																splashAnimation3
																																		.setVisible(false);
																																splashAnimation4
																																		.setVisible(false);
																																splashAnimation5
																																		.setVisible(false);
																																splashAnimation6
																																		.setVisible(false);
																																splashAnimation7
																																		.setVisible(false);
																																splashAnimation8
																																		.setVisible(false);
																																splashAnimation9
																																		.setVisible(false);
																																splashAnimation2
																																		.setVisible(true);
																																splashAnimation2
																																		.animate(
																																				150,
																																				0,
																																				new IAnimationListener() {

																																					@Override
																																					public void onAnimationStarted(
																																							AnimatedSprite pAnimatedSprite,
																																							int pInitialLoopCount) {
																																						// TODO
																																						// Auto-generated
																																						// method
																																						// stub

																																					}

																																					@Override
																																					public void onAnimationLoopFinished(
																																							AnimatedSprite pAnimatedSprite,
																																							int pRemainingLoopCount,
																																							int pInitialLoopCount) {
																																						// TODO
																																						// Auto-generated
																																						// method
																																						// stub

																																					}

																																					@Override
																																					public void onAnimationFrameChanged(
																																							AnimatedSprite pAnimatedSprite,
																																							int pOldFrameIndex,
																																							int pNewFrameIndex) {
																																						// TODO
																																						// Auto-generated
																																						// method
																																						// stub

																																					}

																																					@Override
																																					public void onAnimationFinished(
																																							AnimatedSprite pAnimatedSprite) {
																																						// TODO
																																						// Auto-generated
																																						// method
																																						// stub
																																						splashAnimation0
																																								.setVisible(false);
																																						splashAnimation2
																																								.setVisible(false);
																																						splashAnimation3
																																								.setVisible(false);
																																						splashAnimation4
																																								.setVisible(false);
																																						splashAnimation5
																																								.setVisible(false);
																																						splashAnimation6
																																								.setVisible(false);
																																						splashAnimation7
																																								.setVisible(false);
																																						splashAnimation8
																																								.setVisible(false);
																																						splashAnimation9
																																								.setVisible(false);
																																						splashAnimation1
																																								.setVisible(true);
																																						splashAnimation1
																																								.animate(
																																										150,
																																										0,
																																										new IAnimationListener() {

																																											@Override
																																											public void onAnimationStarted(
																																													AnimatedSprite pAnimatedSprite,
																																													int pInitialLoopCount) {
																																												// TODO
																																												// Auto-generated
																																												// method
																																												// stub

																																											}

																																											@Override
																																											public void onAnimationLoopFinished(
																																													AnimatedSprite pAnimatedSprite,
																																													int pRemainingLoopCount,
																																													int pInitialLoopCount) {
																																												// TODO
																																												// Auto-generated
																																												// method
																																												// stub

																																											}

																																											@Override
																																											public void onAnimationFrameChanged(
																																													AnimatedSprite pAnimatedSprite,
																																													int pOldFrameIndex,
																																													int pNewFrameIndex) {
																																												// TODO
																																												// Auto-generated
																																												// method
																																												// stub

																																											}

																																											@Override
																																											public void onAnimationFinished(
																																													AnimatedSprite pAnimatedSprite) {
																																												// TODO
																																												// Auto-generated
																																												// method
																																												// stub
																																												splashAnimation1
																																														.setVisible(false);
																																												splashAnimation2
																																														.setVisible(false);
																																												splashAnimation3
																																														.setVisible(false);
																																												splashAnimation4
																																														.setVisible(false);
																																												splashAnimation5
																																														.setVisible(false);
																																												splashAnimation6
																																														.setVisible(false);
																																												splashAnimation7
																																														.setVisible(false);
																																												splashAnimation8
																																														.setVisible(false);
																																												splashAnimation9
																																														.setVisible(false);
																																												splashAnimation0
																																														.setVisible(true);
																																												splashAnimation0
																																														.animate(
																																																150,
																																																0,
																																																new IAnimationListener() {

																																																	@Override
																																																	public void onAnimationStarted(
																																																			AnimatedSprite pAnimatedSprite,
																																																			int pInitialLoopCount) {
																																																		// TODO
																																																		// Auto-generated
																																																		// method
																																																		// stub

																																																	}

																																																	@Override
																																																	public void onAnimationLoopFinished(
																																																			AnimatedSprite pAnimatedSprite,
																																																			int pRemainingLoopCount,
																																																			int pInitialLoopCount) {
																																																		// TODO
																																																		// Auto-generated
																																																		// method
																																																		// stub

																																																	}

																																																	@Override
																																																	public void onAnimationFrameChanged(
																																																			AnimatedSprite pAnimatedSprite,
																																																			int pOldFrameIndex,
																																																			int pNewFrameIndex) {
																																																		// TODO
																																																		// Auto-generated
																																																		// method
																																																		// stub

																																																	}

																																																	@Override
																																																	public void onAnimationFinished(
																																																			AnimatedSprite pAnimatedSprite) {
																																																		// TODO
																																																		// Auto-generated
																																																		// method
																																																		// stub
																																																		// if(mMinionKiss.isLoaded())
																																																		// {
																																																		mMinionKiss
																																																				.stop();
																																																		// }
																																																	}
																																																});
																																											}
																																										});
																																					}

																																				});

																															}
																														});
																									}
																								});

																			}
																		});
													}
												});

									}
								});
					}
				});

			}
		});

	}

	private void initBackground() {
		m_Scene = new Scene();
		Sprite sprite = new Sprite(0, 0, mBackgroundTextureRegion,
				mEngine.getVertexBufferObjectManager());
		final float red = 0;
		final float green = 0;
		final float blue = 0;
		SpriteBackground background = new SpriteBackground(red, green, blue,
				sprite);

		m_Scene.setBackground(background);
		m_Scene.setBackgroundEnabled(true);
	}

	public void loadResources() {
		isGameExisted = false;
		texBackground = new BitmapTextureAtlas(this.getTextureManager(), 315,
				560, TextureOptions.BILINEAR);
		textlaughingButton = new BitmapTextureAtlas(this.getTextureManager(),
				256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		textureHornButton = new BitmapTextureAtlas(this.getTextureManager(),
				256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		textFireMinionButton = new BitmapTextureAtlas(this.getTextureManager(),
				256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		textMirrorBrrokenButton = new BitmapTextureAtlas(
				this.getTextureManager(), 256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		/*
		 * texSprite1 = new BitmapTextureAtlas(this.getTextureManager(), 2200,
		 * 1700, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		 */
		// Load all the background and buttons
		texBackground.load();
		textlaughingButton.load();
		textureHornButton.load();
		textFireMinionButton.load();
		textMirrorBrrokenButton.load();
		// texSprite1.load();
		BuildableBitmapTextureAtlas bitmapTextureAtlas = new BuildableBitmapTextureAtlas(
				mEngine.getTextureManager(), 600, 100);
		mBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(texBackground, getAssets(), "background.png",
						0, 0);
		mLaughMinion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				textlaughingButton, getAssets(), "gfx/spr/laugh.png", 0, 0);
		mMinionHorn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				textureHornButton, getAssets(), "gfx/spr/horn.png", 0, 0);
		mMinionFire = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				textFireMinionButton, getAssets(), "gfx/spr/fire.png", 0, 0);
		mMirrorBroken = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				textMirrorBrrokenButton, getAssets(),
				"gfx/spr/brokenmirror.png", 0, 0);

		try {
			bitmapTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 0, 0));
		} catch (TextureAtlasBuilderException e) {
			e.printStackTrace();
		}

		/* Load the bitmap texture atlas */
		bitmapTextureAtlas.load();
		SoundFactory.setAssetBasePath("gfx/");
		MusicFactory.setAssetBasePath("gfx/");
		try {
			mMinionLaugh = SoundFactory.createSoundFromAsset(getSoundManager(),
					this, "sound/minionlaugh.mp3");
			/*
			 * mMinionHelloSound = SoundFactory.createSoundFromAsset(
			 * getSoundManager(), this, "sound/hello.mp3");
			 */
			mMinionFrightenedSound = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "sound/minionfrigh.mp3");
			mMinionHornSound = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "sound/minionhorn.mp3");
			mMinionHitSound = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "sound/minionhit.mp3");
			mMirrorBrokenSound = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "sound/mirrorbroken.mp3");
			mMinionScreamingSound = SoundFactory.createSoundFromAsset(
					getSoundManager(), this, "sound/minionscreaming.mp3");
			mRPGSound = SoundFactory.createSoundFromAsset(getSoundManager(),
					this, "sound/rpgsound.mp3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		mMinionLaugh.setVolume(mEngine.getSoundManager().getMasterVolume());
		mMinionLaugh.setLooping(true);

		/*
		 * mMinionHelloSound
		 * .setVolume(mEngine.getSoundManager().getMasterVolume());
		 * mMinionHelloSound.setLooping(true);
		 */
		mMinionHornSound.setVolume(mEngine.getSoundManager().getMasterVolume());
		mMinionHornSound.setLooping(true);

		mMinionFrightenedSound.setVolume(mEngine.getSoundManager()
				.getMasterVolume());
		mMinionFrightenedSound.setLooping(true);

		mMinionHitSound.setVolume(mEngine.getSoundManager().getMasterVolume());
		mMinionHitSound.setLooping(true);

		mMirrorBrokenSound.setVolume(mEngine.getSoundManager()
				.getMasterVolume());
		mMirrorBrokenSound.setLooping(true);

		mMinionScreamingSound.setVolume(mEngine.getSoundManager()
				.getMasterVolume());
		mMinionScreamingSound.setLooping(true);

		mRPGSound.setVolume(mEngine.getSoundManager().getMasterVolume());
		mRPGSound.setLooping(true);

	}

	private void loadScenes() {
		isLaughing = false;
		isPlayingHorn = false;
		isMinionFrightened = false;
		isMinionHitted = false;
		isMirrorBroken = false;
		isMinionFired = false;
		idleMinionEntity = new Entity();
		 
		// ///////////////////////////////////////////////////////
		minionHornButton = new ButtonSprite(CAMERA_WIDTH * 0.5f + 110,
				CAMERA_HEIGHT * 0.5f + 40, mMinionHorn,
				mEngine.getVertexBufferObjectManager()) {

			/* Override the onAreaTouched() event method */
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				/* If buttonSprite is touched with the finger */
				if (pSceneTouchEvent.isActionDown()) {

					/*
					 * When the button is pressed, we can create an event In
					 * this case, we're simply displaying a quick toast
					 */
					MainActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							/*
							 * Toast.makeText(getApplicationContext(),
							 * "Button Pressed!", Toast.LENGTH_SHORT) .show();
							 */
							// m_Scene.attachChild(hornMinionEntity);
							if (speakMinionEntity.isVisible()) {
								speakMinionEntity.setVisible(false);
							}
							if (hitMinionEntity.isVisible()) {
								hitMinionEntity.setVisible(false);
							}
							if (idleMinionEntity.isVisible()) {
								idleMinionEntity.setVisible(false);

							}
							if (laughMinionEntity.isVisible()) {
								laughMinionEntity.setVisible(false);

							}
							if (isLaughing) {
								isLaughing = false;
							}
							if (isMinionFrightened) {
								isMinionFrightened = false;
							}
							if (!isPlayingHorn) {
								isPlayingHorn = true;
							}
							/*
							 * if (isPlayingHello) { isPlayingHello = false; }
							 */
							if (isMinionHitted) {
								isMinionHitted = false;
							}
							if (isMinionFired) {
								isMinionFired = false;
							}
							if (isMirrorBroken) {
								isMirrorBroken = false;
							}

							if (mMinionScreamingSound.isLoaded()) {
								mMinionScreamingSound.stop();
							}
							if (mRPGSound.isLoaded()) {
								mRPGSound.stop();
							}
							if (mMirrorBrokenSound.isLoaded()) {
								mMirrorBrokenSound.stop();
							}

							if (mMinionLaugh.isLoaded()) {
								mMinionLaugh.stop();
							}
							if (mMinionHitSound.isLoaded()) {
								mMinionHitSound.stop();
							}
							if (mMinionFrightenedSound.isLoaded()) {
								mMinionFrightenedSound.stop();
							}
							/*
							 * if (mMinionHelloSound.isLoaded()) {
							 * mMinionHelloSound.stop(); }
							 */
							/*
							 * if(mIdleMinionSound.isLoaded()) {
							 * mIdleMinionSound.stop(); }
							 */
							if (hornMinionEntity.isVisible()) {
								hornMinionEntity.setVisible(false);
							}
							if (shakeMinionRightEntity.isVisible()) {
								shakeMinionRightEntity.setVisible(false);
							}
							if (shakeMinionLeftEntity.isVisible()) {
								shakeMinionLeftEntity.setVisible(false);
							}
							if (mirrorBrokeEntity.isVisible()) {
								mirrorBrokeEntity.setVisible(false);
							}
							if (fireMinionEntity.isVisible()) {
								fireMinionEntity.setVisible(false);
							}

							hornMinionEntity.setVisible(true);
							animateHornSprite();

						}
					});
				}
				if (pSceneTouchEvent.isActionUp()) {

				}

				/*
				 * In order to allow the ButtonSprite to swap tiled texture
				 * region index on our buttonSprite object, we must return the
				 * super method
				 */
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		// m_Scene.attachChild(new Entity());
		minionLaughButton = new ButtonSprite(CAMERA_WIDTH * 0.5f + 115,
				CAMERA_HEIGHT * 0.5f - 10, mLaughMinion,
				mEngine.getVertexBufferObjectManager()) {

			/* Override the onAreaTouched() event method */
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				/* If buttonSprite is touched with the finger */
				if (pSceneTouchEvent.isActionDown()) {

					/*
					 * When the button is pressed, we can create an event In
					 * this case, we're simply displaying a quick toast
					 */
					MainActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							/*
							 * Toast.makeText(getApplicationContext(),
							 * "Button Pressed!", Toast.LENGTH_SHORT) .show();
							 */
							if (speakMinionEntity.isVisible()) {
								speakMinionEntity.setVisible(false);
							}
							if (hitMinionEntity.isVisible()) {
								hitMinionEntity.setVisible(false);
							}
							/*
							 * if(mIdleMinionSound.isLoaded()) {
							 * mIdleMinionSound.stop(); }
							 */
							if (!isLaughing) {
								isLaughing = true;
							}
							if (isPlayingHorn) {
								isPlayingHorn = false;
							}
							if (isMinionFrightened) {
								isMinionFrightened = false;
							}
							/*
							 * if (isPlayingHello) { isPlayingHello = false; }
							 */
							if (isMinionHitted) {
								isMinionHitted = false;
							}
							if (isMinionFired) {
								isMinionFired = false;
							}
							if (isMirrorBroken) {
								isMirrorBroken = false;
							}

							if (mMinionScreamingSound.isLoaded()) {
								mMinionScreamingSound.stop();
							}
							if (mRPGSound.isLoaded()) {
								mRPGSound.stop();
							}
							if (mMirrorBrokenSound.isLoaded()) {
								mMirrorBrokenSound.stop();
							}

							if (mMinionHornSound.isLoaded()) {
								mMinionHornSound.stop();
							}
							/*
							 * if (mMinionHelloSound.isLoaded()) {
							 * mMinionHelloSound.stop(); }
							 */
							if (mMinionFrightenedSound.isLoaded()) {
								mMinionFrightenedSound.stop();
							}
							if (mMinionHitSound.isLoaded()) {
								mMinionHitSound.stop();
							}
							if (laughMinionEntity.isVisible()) {
								laughMinionEntity.setVisible(false);
							}
							if (hornMinionEntity.isVisible()) {
								hornMinionEntity.setVisible(false);
							}
							if (idleMinionEntity.isVisible()) {
								idleMinionEntity.setVisible(false);
							}
							if (shakeMinionRightEntity.isVisible()) {
								shakeMinionRightEntity.setVisible(false);
							}
							if (shakeMinionLeftEntity.isVisible()) {
								shakeMinionLeftEntity.setVisible(false);
							}
							if (mirrorBrokeEntity.isVisible()) {
								mirrorBrokeEntity.setVisible(false);
							}
							if (fireMinionEntity.isVisible()) {
								fireMinionEntity.setVisible(false);
							}
							laughMinionEntity.setVisible(true);
							animateLaughingSprite();
						}
					});
				}
				if (pSceneTouchEvent.isActionUp()) {

				}

				/*
				 * In order to allow the ButtonSprite to swap tiled texture
				 * region index on our buttonSprite object, we must return the
				 * super method
				 */
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		fireMinionButton = new ButtonSprite(CAMERA_WIDTH * 0.5f + 105,
				CAMERA_HEIGHT * 0.5f + 78, mMinionFire,
				mEngine.getVertexBufferObjectManager()) {

			/* Override the onAreaTouched() event method */
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				/* If buttonSprite is touched with the finger */
				if (pSceneTouchEvent.isActionDown()) {

					/*
					 * When the button is pressed, we can create an event In
					 * this case, we're simply displaying a quick toast
					 */
					MainActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (!isMinionFired) {
								isMinionFired = true;
							}
							if (speakMinionEntity.isVisible()) {
								speakMinionEntity.setVisible(false);
							}
							if (hitMinionEntity.isVisible()) {
								hitMinionEntity.setVisible(false);
							}
							/*
							 * if(mIdleMinionSound.isLoaded()) {
							 * mIdleMinionSound.stop(); }
							 */
							if (isLaughing) {
								isLaughing = false;
							}
							if (isPlayingHorn) {
								isPlayingHorn = false;
							}
							if (isMinionFrightened) {
								isMinionFrightened = false;
							}
							/*
							 * if (isPlayingHello) { isPlayingHello = false; }
							 */
							if (isMinionHitted) {
								isMinionHitted = false;
							}
							if (isMirrorBroken) {
								isMirrorBroken = false;
							}

							if (mMinionScreamingSound.isLoaded()) {
								mMinionScreamingSound.stop();
							}
							if (mRPGSound.isLoaded()) {
								mRPGSound.stop();
							}
							if (mMirrorBrokenSound.isLoaded()) {
								mMirrorBrokenSound.stop();
							}
							if (mMinionHornSound.isLoaded()) {
								mMinionHornSound.stop();
							}
							/*
							 * if (mMinionHelloSound.isLoaded()) {
							 * mMinionHelloSound.stop(); }
							 */
							if (mMinionLaugh.isLoaded()) {
								mMinionLaugh.stop();
							}
							if (mMinionFrightenedSound.isLoaded()) {
								mMinionFrightenedSound.stop();
							}
							if (mMinionHitSound.isLoaded()) {
								mMinionHitSound.stop();
							}
							if (laughMinionEntity.isVisible()) {
								laughMinionEntity.setVisible(false);
							}
							if (hornMinionEntity.isVisible()) {
								hornMinionEntity.setVisible(false);
							}
							if (idleMinionEntity.isVisible()) {
								idleMinionEntity.setVisible(false);
							}
							if (shakeMinionRightEntity.isVisible()) {
								shakeMinionRightEntity.setVisible(false);
							}
							if (shakeMinionLeftEntity.isVisible()) {
								shakeMinionLeftEntity.setVisible(false);
							}
							if (mirrorBrokeEntity.isVisible()) {
								mirrorBrokeEntity.setVisible(false);
							}
							if (fireMinionEntity.isVisible()) {
								fireMinionEntity.setVisible(false);
							}

							fireMinionEntity.setVisible(true);
							animateMinionFire();
						}
					});
				}
				if (pSceneTouchEvent.isActionUp()) {

				}

				/*
				 * In order to allow the ButtonSprite to swap tiled texture
				 * region index on our buttonSprite object, we must return the
				 * super method
				 */
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		mirrorBroke = new ButtonSprite(CAMERA_WIDTH * 0.5f + 110,
				CAMERA_HEIGHT * 0.5f + 112, mMirrorBroken,
				mEngine.getVertexBufferObjectManager()) {

			/* Override the onAreaTouched() event method */
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				/* If buttonSprite is touched with the finger */
				if (pSceneTouchEvent.isActionDown()) {

					/*
					 * When the button is pressed, we can create an event In
					 * this case, we're simply displaying a quick toast
					 */
					MainActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (speakMinionEntity.isVisible()) {
								speakMinionEntity.setVisible(false);
							}
							if (hitMinionEntity.isVisible()) {
								hitMinionEntity.setVisible(false);
							}
							/*
							 * if(mIdleMinionSound.isLoaded()) {
							 * mIdleMinionSound.stop(); }
							 */
							if (isLaughing) {
								isLaughing = false;
							}
							if (isPlayingHorn) {
								isPlayingHorn = false;
							}
							if (isMinionFrightened) {
								isMinionFrightened = false;
							}
							if (isMinionFired) {
								isMinionFired = false;
							}
							if (!isMirrorBroken) {
								isMirrorBroken = true;
							}
							if (isMinionHitted) {
								isMinionHitted = false;
							}
							if (mMinionHornSound.isLoaded()) {
								mMinionHornSound.stop();
							}
							if (mMinionFrightenedSound.isLoaded()) {
								mMinionFrightenedSound.stop();
							}
							if (mMinionHitSound.isLoaded()) {
								mMinionHitSound.stop();
							}
							if (mMinionLaugh.isLoaded()) {
								mMinionLaugh.stop();
							}
							if (laughMinionEntity.isVisible()) {
								laughMinionEntity.setVisible(false);
							}
							if (hornMinionEntity.isVisible()) {
								hornMinionEntity.setVisible(false);
							}
							if (idleMinionEntity.isVisible()) {
								idleMinionEntity.setVisible(false);
							}
							if (shakeMinionRightEntity.isVisible()) {
								shakeMinionRightEntity.setVisible(false);
							}
							if (shakeMinionLeftEntity.isVisible()) {
								shakeMinionLeftEntity.setVisible(false);
							}
							if (mirrorBrokeEntity.isVisible()) {
								mirrorBrokeEntity.setVisible(false);
							}
							if (fireMinionEntity.isVisible()) {
								fireMinionEntity.setVisible(false);
							}
							minionHornButton.setVisible(false);
							minionLaughButton.setVisible(false);
							fireMinionButton.setVisible(false);
							mirrorBroke.setVisible(false);
							mirrorBrokeEntity.setVisible(true);
							animateBrokenMirror();
						}
					});
				}
				if (pSceneTouchEvent.isActionUp()) {

				}

				/*
				 * In order to allow the ButtonSprite to swap tiled texture
				 * region index on our buttonSprite object, we must return the
				 * super method
				 */
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		m_Scene.registerTouchArea(minionLaughButton);
		m_Scene.registerTouchArea(minionHornButton);
		m_Scene.registerTouchArea(fireMinionButton);
		m_Scene.registerTouchArea(mirrorBroke);
		m_Scene.attachChild(minionLaughButton);
		m_Scene.attachChild(minionHornButton);
		m_Scene.attachChild(fireMinionButton);
		m_Scene.attachChild(mirrorBroke);
		minionHornButton.setZIndex(1);
		minionLaughButton.setZIndex(1);
		fireMinionButton.setZIndex(1);
		mirrorBroke.setZIndex(1);
		/********************* Shake Animation Left ************************/
		shakeMinionLeftEntity = new Entity();
		shakeMinionLeft0 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("0,1shakeminionleft"),
				mEngine.getVertexBufferObjectManager());
		shakeMinionLeft1 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("1,2shakeminionleft"),
				mEngine.getVertexBufferObjectManager());
		shakeMinionLeft2 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("2,3shakeminionleft"),
				mEngine.getVertexBufferObjectManager());
		shakeMinionLeft3 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("3,4shakeminionleft"),
				mEngine.getVertexBufferObjectManager());
		shakeMinionLeftEntity.attachChild(shakeMinionLeft0);
		shakeMinionLeftEntity.attachChild(shakeMinionLeft1);
		shakeMinionLeftEntity.attachChild(shakeMinionLeft2);
		shakeMinionLeftEntity.attachChild(shakeMinionLeft3);
		shakeMinionLeftEntity.setVisible(false);
		m_Scene.attachChild(shakeMinionLeftEntity);
		/********************** Shake Animation Right ***********************/
		shakeMinionRightEntity = new Entity();
		shakeMinionRight0 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("0,1shakeminionright"),
				mEngine.getVertexBufferObjectManager());
		shakeMinionRight1 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("1,2shakeminionright"),
				mEngine.getVertexBufferObjectManager());
		shakeMinionRight2 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("2,3shakeminionright"),
				mEngine.getVertexBufferObjectManager());
		shakeMinionRight3 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("3,4shakeminionright"),
				mEngine.getVertexBufferObjectManager());
		shakeMinionRightEntity.attachChild(shakeMinionRight0);
		shakeMinionRightEntity.attachChild(shakeMinionRight1);
		shakeMinionRightEntity.attachChild(shakeMinionRight2);
		shakeMinionRightEntity.attachChild(shakeMinionRight3);
		shakeMinionRightEntity.setVisible(false);
		m_Scene.attachChild(shakeMinionRightEntity);
		/********************* Fire Minion *******************************/
		fireMinionEntity = new Entity();
		fireMinion0 = new AnimatedSprite(50, 230,
				getTiledTextureFromPack("0,1fireminion"),
				mEngine.getVertexBufferObjectManager());
		fireMinion1 = new AnimatedSprite(-60, 250,
				getTiledTextureFromPack("1,2fireminion"),
				mEngine.getVertexBufferObjectManager());
		fireMinionEntity.attachChild(fireMinion0);
		fireMinionEntity.attachChild(fireMinion1);
		fireMinionEntity.setVisible(false);
		m_Scene.attachChild(fireMinionEntity);
		/********************* Mirror Broke ********************************/
		mirrorBrokeEntity = new Entity();
		mirrorBroke2 = new AnimatedSprite(-100, 280,
				getTiledTextureFromPack("2,3mirrorbroke"),
				mEngine.getVertexBufferObjectManager());
		mirrorBroke3 = new AnimatedSprite(-100, 280,
				getTiledTextureFromPack("3,4mirrorbroke"),
				mEngine.getVertexBufferObjectManager());
		mirrorBroke4 = new AnimatedSprite(-100, 280,
				getTiledTextureFromPack("4,5mirrorbroke"),
				mEngine.getVertexBufferObjectManager());
		mirrorBrokeEntity.attachChild(mirrorBroke2);
		mirrorBrokeEntity.attachChild(mirrorBroke3);
		mirrorBrokeEntity.attachChild(mirrorBroke4);
		mirrorBrokeEntity.setVisible(false);
		m_Scene.attachChild(mirrorBrokeEntity);
		/********************** Speak Minion ********************************/
		speakMinionEntity = new Entity();
		speakMinion0 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("0,1speakminion"),
				mEngine.getVertexBufferObjectManager());
		speakMinion1 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("1,2speakminion"),
				mEngine.getVertexBufferObjectManager());
		speakMinion2 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("2,3speakminion"),
				mEngine.getVertexBufferObjectManager());
		speakMinionEntity.attachChild(speakMinion0);
		speakMinionEntity.attachChild(speakMinion1);
		speakMinionEntity.attachChild(speakMinion2);
		speakMinionEntity.setVisible(false);
		speakMinionEntity.registerUpdateHandler(new IUpdateHandler() {

			@Override
			public void reset() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				if ((isPlaying == true)
						&& (speakMinionEntity.isVisible() == false)
						&& (!laughMinionEntity.isVisible())
						&& (!hitMinionEntity.isVisible())
						&& (!shakeMinionLeftEntity.isVisible())
						&& (!shakeMinionRightEntity.isVisible())
						&& (!fireMinionEntity.isVisible())
						&& (!mirrorBrokeEntity.isVisible())
						&& (!hornMinionEntity.isVisible())) {
					animationSpeakSprite();
				}
			}
		});
		m_Scene.attachChild(speakMinionEntity);
		/*********************** Minion Horn ****************************************/
		minionHorn0 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("0,1pipeminion"),
				mEngine.getVertexBufferObjectManager());
		minionHorn1 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("1,2pipeminion"),
				mEngine.getVertexBufferObjectManager());
		minionHorn2 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("2,3pipeminion"),
				mEngine.getVertexBufferObjectManager());
		minionHorn3 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("3,4pipeminion"),
				mEngine.getVertexBufferObjectManager());
		minionHorn4 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("4,5pipeminion"),
				mEngine.getVertexBufferObjectManager());
		minionHorn5 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("5,6pipeminion"),
				mEngine.getVertexBufferObjectManager());

		/*********************** Minion Laugh *****************************************/
		minionLaugh0 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("0,1laughminion"),
				mEngine.getVertexBufferObjectManager());
		minionLaugh1 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("1,2laughminion"),
				mEngine.getVertexBufferObjectManager());
		minionLaugh3 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("2,3laughminion"),
				mEngine.getVertexBufferObjectManager());
		minionLaugh4 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("3,4laughminion"),
				mEngine.getVertexBufferObjectManager());
		minionLaugh5 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("4,5laughminion"),
				mEngine.getVertexBufferObjectManager());
		minionLaugh6 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("5,6laughminion"),
				mEngine.getVertexBufferObjectManager());
		minionLaugh7 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("6,7laughminion"),
				mEngine.getVertexBufferObjectManager());

		/****************** hit Minion ****************************/
		hitMinionEntity = new Entity();
		hitMinion = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("hitminion"),
				mEngine.getVertexBufferObjectManager());
		hitMinionEntity.attachChild(hitMinion);
		hitMinionEntity.setVisible(false);
		m_Scene.attachChild(hitMinionEntity);
		/******************* idle Minion ***************************/
		isAnimating = false;
		idleMinion0 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("0,1idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					MainActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// if (pSceneTouchEvent.isActionDown()) {
							if (idleMinionEntity.isVisible()) {
								/*
								 * if (mMinionHelloSound.isLoaded()) {
								 * mMinionHelloSound.stop(); }
								 */
								if (mMinionHornSound.isLoaded()) {
									mMinionHornSound.stop();
								}
								if (isPlayingHorn) {
									isPlayingHorn = false;
								}
								/*
								 * if (isPlayingHello) { isPlayingHello = false;
								 * }
								 */
								if (!isMinionHitted) {
									isMinionHitted = true;
								}
								hitMinionEntity.setVisible(true);
								idleMinionEntity.setVisible(false);
								animateHitMinion();
							}
							// }
						}
					});
				}
				return true;

			}
		};
		idleMinion1 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("1,2idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

				return true;

			}
		};
		idleMinion2 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("2,3idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/*
				 * if(pSceneTouchEvent.isActionDown()) {
				 * MainActivity.this.runOnUiThread(new Runnable() {
				 * 
				 * @Override public void run() {
				 * Toast.makeText(getApplicationContext(), "Minion Hit!",
				 * Toast.LENGTH_SHORT) .show(); }}); }
				 */
				return true;

			}
		};
		idleMinion3 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("3,4idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/*
				 * if(pSceneTouchEvent.isActionDown()) {
				 * Toast.makeText(getApplicationContext(), "Minion Hit!",
				 * Toast.LENGTH_SHORT) .show(); }
				 */
				return true;

			}
		};
		idleMinion4 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("4,5idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/*
				 * if(pSceneTouchEvent.isActionDown()) {
				 * Toast.makeText(getApplicationContext(), "Minion Hit!",
				 * Toast.LENGTH_SHORT) .show(); }
				 */
				return true;

			}
		};
		idleMinion5 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("5,6idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/*
				 * if(pSceneTouchEvent.isActionDown()) {
				 * Toast.makeText(getApplicationContext(), "Minion Hit!",
				 * Toast.LENGTH_SHORT) .show(); }
				 */
				return true;

			}
		};
		idleMinion6 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("6,7idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/*
				 * if(pSceneTouchEvent.isActionDown()) {
				 * Toast.makeText(getApplicationContext(), "Minion Hit!",
				 * Toast.LENGTH_SHORT) .show(); }
				 */
				return true;

			}
		};
		idleMinion7 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("7,8idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/*
				 * if(pSceneTouchEvent.isActionDown()) {
				 * Toast.makeText(getApplicationContext(), "Minion Hit!",
				 * Toast.LENGTH_SHORT) .show(); }
				 */
				return true;

			}
		};
		idleMinion8 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("8,9idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/*
				 * if(pSceneTouchEvent.isActionDown()) {
				 * Toast.makeText(getApplicationContext(), "Minion Hit!",
				 * Toast.LENGTH_SHORT) .show(); }
				 */
				return true;

			}
		};
		idleMinion9 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("9,10idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				/*
				 * if(pSceneTouchEvent.isActionDown()) {
				 * Toast.makeText(getApplicationContext(), "Minion Hit!",
				 * Toast.LENGTH_SHORT) .show(); }
				 */
				return true;

			}
		};
		idleMinion10 = new AnimatedSprite(0, 150,
				getTiledTextureFromPack("10,11idleminion"),
				mEngine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				return true;

			}
		};
		m_Scene.registerTouchArea(idleMinion0);
		m_Scene.setTouchAreaBindingOnActionDownEnabled(true);
		idleMinionEntity.attachChild(idleMinion0);
		idleMinionEntity.attachChild(idleMinion1);
		idleMinionEntity.attachChild(idleMinion2);
		idleMinionEntity.attachChild(idleMinion3);
		idleMinionEntity.attachChild(idleMinion4);
		idleMinionEntity.attachChild(idleMinion5);
		idleMinionEntity.attachChild(idleMinion6);
		idleMinionEntity.attachChild(idleMinion7);
		idleMinionEntity.attachChild(idleMinion8);
		idleMinionEntity.attachChild(idleMinion9);
		idleMinionEntity.attachChild(idleMinion10);

		idleMinionEntity.setVisible(false);

		idleMinionEntity.registerUpdateHandler(new IUpdateHandler() {

			@Override
			public void reset() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				Log.i("TAG", "isListeningisListeningisListeningisListeningisListening:  "+isListening);
				Log.i("TAG", "isLaughingisLaughingisLaughingisLaughing:  "+isLaughing);
				if (idleMinionEntity.isVisible()
						&& !isListening
						&& isAnimating
					   && !isMinionFired && !isMirrorBroken &&  !isMinionHitted && !isLaughing && !isPlayingHorn && !isMinionFrightened) {
					Start();
				}
				if ((isAnimating == false)) {
				
					isAnimating = true;
					if (!laughMinionEntity.isVisible()
							&& !hornMinionEntity.isVisible()
							&& !shakeMinionLeftEntity.isVisible()
							&& !shakeMinionRightEntity.isVisible()
							&& !hitMinionEntity.isVisible()
							&& (!fireMinionEntity.isVisible())
							&& (!mirrorBrokeEntity.isVisible())) {
						idleMinionEntity.setVisible(true);
					}
					MainActivity.this.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							animateIdleSprite();

						}
					});
					// idleMinionEntity.reset();

				}
				

			}
		});

		m_Scene.attachChild(idleMinionEntity);

		/***************************** Laughing Entity ********************/
		laughMinionEntity = new Entity();
		laughMinionEntity.attachChild(minionLaugh0);
		laughMinionEntity.attachChild(minionLaugh1);
		laughMinionEntity.attachChild(minionLaugh3);
		laughMinionEntity.attachChild(minionLaugh4);
		laughMinionEntity.attachChild(minionLaugh5);
		laughMinionEntity.attachChild(minionLaugh6);
		laughMinionEntity.attachChild(minionLaugh7);
		laughMinionEntity.setVisible(false);
		m_Scene.attachChild(laughMinionEntity);
		/*************************** Horn Entity **************************/
		hornMinionEntity = new Entity();
		hornMinionEntity.setVisible(false);
		hornMinionEntity.attachChild(minionHorn0);
		hornMinionEntity.attachChild(minionHorn1);
		hornMinionEntity.attachChild(minionHorn2);
		hornMinionEntity.attachChild(minionHorn3);
		hornMinionEntity.attachChild(minionHorn4);
		hornMinionEntity.attachChild(minionHorn5);
		hornMinionEntity.setVisible(false);
		m_Scene.attachChild(hornMinionEntity);
		this.enableAccelerationSensor(this);
		m_Scene.sortChildren();
		// Start();
		//
	//	animateIdleSprite();
	}

	public void animateBrokenMirror() {
		// mirrorBroke0.setVisible(false);
		// mirrorBroke1.setVisible(false);
		mirrorBroke2.setVisible(false);
		mirrorBroke3.setVisible(false);
		mirrorBroke4.setVisible(true);
		if (mMirrorBrokenSound.isLoaded()) {
			mMirrorBrokenSound.stop();
			mMirrorBrokenSound.play();
		}
		mirrorBroke4.animate(200, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				// mirrorBroke0.setVisible(false);
				// mirrorBroke1.setVisible(false);
				mirrorBroke2.setVisible(false);
				mirrorBroke4.setVisible(false);
				mirrorBroke3.setVisible(true);
				mirrorBroke3.animate(200, 0, new IAnimationListener() {

					@Override
					public void onAnimationStarted(
							AnimatedSprite pAnimatedSprite,
							int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationLoopFinished(
							AnimatedSprite pAnimatedSprite,
							int pRemainingLoopCount, int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFrameChanged(
							AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
							int pNewFrameIndex) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// TODO Auto-generated method stub
						// mirrorBroke0.setVisible(false);
						// mirrorBroke1.setVisible(false);
						mirrorBroke4.setVisible(false);
						mirrorBroke3.setVisible(false);
						mirrorBroke2.setVisible(true);
						mirrorBroke2.animate(200, 0, new IAnimationListener() {

							@Override
							public void onAnimationStarted(
									AnimatedSprite pAnimatedSprite,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationLoopFinished(
									AnimatedSprite pAnimatedSprite,
									int pRemainingLoopCount,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFrameChanged(
									AnimatedSprite pAnimatedSprite,
									int pOldFrameIndex, int pNewFrameIndex) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFinished(
									AnimatedSprite pAnimatedSprite) {
								// TODO Auto-generated method stub

								isMirrorBroken = false;
								mirrorBrokeEntity.setVisible(false);
								mMirrorBrokenSound.stop();
								// ////////// button//////////////
								minionHornButton.setVisible(true);
								minionLaughButton.setVisible(true);
								fireMinionButton.setVisible(true);
								mirrorBroke.setVisible(true);
								// //////////////////////////////
								if ((laughMinionEntity.isVisible() == false)
										&& (hornMinionEntity.isVisible() == false)
										&& (!shakeMinionRightEntity.isVisible())
										&& (!shakeMinionLeftEntity.isVisible())
										&& (!fireMinionEntity.isVisible())
										&& (!mirrorBrokeEntity.isVisible())) {
									idleMinionEntity.setVisible(true);
								}
							}
						});
					}
				});
			}
		});
	}

	public void animateMinionFire() {

		fireMinion1.setVisible(false);
		fireMinion0.setVisible(true);
		if (mRPGSound.isLoaded()) {
			mRPGSound.stop();
			mRPGSound.play();
		}
		fireMinion0.animate(200, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub
				if (pNewFrameIndex == 3) {
					if (mRPGSound.isLoaded()) {
						mRPGSound.stop();
					}
					if (mMinionScreamingSound.isLoaded()) {
						mMinionScreamingSound.stop();
						mMinionScreamingSound.play();
					}
				}
			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				fireMinion0.setVisible(false);
				fireMinion1.setVisible(true);
				fireMinion1.animate(200, 0, new IAnimationListener() {

					@Override
					public void onAnimationStarted(
							AnimatedSprite pAnimatedSprite,
							int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationLoopFinished(
							AnimatedSprite pAnimatedSprite,
							int pRemainingLoopCount, int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFrameChanged(
							AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
							int pNewFrameIndex) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// TODO Auto-generated method stub
						fireMinionEntity.setVisible(false);
						mMinionScreamingSound.stop();
						isMinionFired = false;
						if ((laughMinionEntity.isVisible() == false)
								&& (hornMinionEntity.isVisible() == false)
								&& (!shakeMinionRightEntity.isVisible())
								&& (!shakeMinionLeftEntity.isVisible())
								&& (!fireMinionEntity.isVisible())
								&& (!mirrorBrokeEntity.isVisible())) {
							idleMinionEntity.setVisible(true);
						}
					}
				});

			}
		});
	}

	public void animateHitMinion() {
		hitMinionEntity.setVisible(true);
		if (mMinionHitSound.isLoaded()) {
			mMinionHitSound.stop();
			mMinionHitSound.play();
		}
		if (!mMinionHitSound.isLoaded()) {
			mMinionHitSound.play();
		}
		hitMinion.animate(200, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				hitMinionEntity.setVisible(false);
				isMinionHitted = false;
				if ((laughMinionEntity.isVisible() == false)
						&& (hornMinionEntity.isVisible() == false)
						&& (!shakeMinionRightEntity.isVisible())
						&& (!shakeMinionLeftEntity.isVisible())
						&& (!fireMinionEntity.isVisible())
						&& (!mirrorBrokeEntity.isVisible()
								&& (!fireMinionEntity.isVisible()) && (!mirrorBrokeEntity
									.isVisible()))) {
					idleMinionEntity.setVisible(true);
				}
				if (mMinionHitSound.isLoaded()) {
					mMinionHitSound.stop();

				}
			}

		});

	}

	public void animateShakeMinionRight() {
		shakeMinionRightEntity.setVisible(true);
		if (mMinionFrightenedSound.isLoaded()) {
			mMinionFrightenedSound.stop();
			mMinionFrightenedSound.play();
		}
		shakeMinionRight1.setVisible(false);
		shakeMinionRight2.setVisible(false);
		shakeMinionRight3.setVisible(false);
		shakeMinionRight0.setVisible(true);
		shakeMinionRight0.animate(200, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				shakeMinionRight0.setVisible(false);
				shakeMinionRight2.setVisible(false);
				shakeMinionRight3.setVisible(false);
				shakeMinionRight1.setVisible(true);
				shakeMinionRight1.animate(200, 0, new IAnimationListener() {

					@Override
					public void onAnimationStarted(
							AnimatedSprite pAnimatedSprite,
							int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationLoopFinished(
							AnimatedSprite pAnimatedSprite,
							int pRemainingLoopCount, int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFrameChanged(
							AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
							int pNewFrameIndex) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// TODO Auto-generated method stub
						shakeMinionRight0.setVisible(false);
						shakeMinionRight1.setVisible(false);
						shakeMinionRight3.setVisible(false);
						shakeMinionRight2.setVisible(true);
						shakeMinionRight2.animate(200, 0,
								new IAnimationListener() {

									@Override
									public void onAnimationStarted(
											AnimatedSprite pAnimatedSprite,
											int pInitialLoopCount) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationLoopFinished(
											AnimatedSprite pAnimatedSprite,
											int pRemainingLoopCount,
											int pInitialLoopCount) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationFrameChanged(
											AnimatedSprite pAnimatedSprite,
											int pOldFrameIndex,
											int pNewFrameIndex) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationFinished(
											AnimatedSprite pAnimatedSprite) {
										// TODO Auto-generated method stub
										shakeMinionRight0.setVisible(false);
										shakeMinionRight1.setVisible(false);
										shakeMinionRight2.setVisible(false);
										shakeMinionRight3.setVisible(true);
										shakeMinionRight3.animate(200, 0,
												new IAnimationListener() {

													@Override
													public void onAnimationStarted(
															AnimatedSprite pAnimatedSprite,
															int pInitialLoopCount) {
														// TODO Auto-generated
														// method stub

													}

													@Override
													public void onAnimationLoopFinished(
															AnimatedSprite pAnimatedSprite,
															int pRemainingLoopCount,
															int pInitialLoopCount) {
														// TODO Auto-generated
														// method stub

													}

													@Override
													public void onAnimationFrameChanged(
															AnimatedSprite pAnimatedSprite,
															int pOldFrameIndex,
															int pNewFrameIndex) {
														// TODO Auto-generated
														// method stub

													}

													@Override
													public void onAnimationFinished(
															AnimatedSprite pAnimatedSprite) {
														// TODO Auto-generated
														// method stub
														if (isMinionFrightened) {
															isMinionFrightened = false;
														}
														if ((hitMinionEntity
																.isVisible() == false)
																&& (hornMinionEntity
																		.isVisible() == false)
																&& (shakeMinionLeftEntity
																		.isVisible() == false)
																&& (laughMinionEntity
																		.isVisible() == false)
																&& (!fireMinionEntity
																		.isVisible())
																&& (!mirrorBrokeEntity
																		.isVisible())) {
															idleMinionEntity
																	.setVisible(true);
														}
														shakeMinionRightEntity
																.setVisible(false);
														if (mMinionFrightenedSound
																.isLoaded()) {
															mMinionFrightenedSound
																	.stop();
														}
													}
												});
									}
								});
					}
				});
			}
		});

	}

	public void animateShakeMinionLeft() {
		shakeMinionLeftEntity.setVisible(true);
		if (mMinionFrightenedSound.isLoaded()) {
			mMinionFrightenedSound.stop();
			mMinionFrightenedSound.play();
		}
		shakeMinionLeft1.setVisible(false);
		shakeMinionLeft2.setVisible(false);
		shakeMinionLeft3.setVisible(false);
		shakeMinionLeft0.setVisible(true);
		shakeMinionLeft0.animate(200, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				shakeMinionLeft0.setVisible(false);
				shakeMinionLeft2.setVisible(false);
				shakeMinionLeft3.setVisible(false);
				shakeMinionLeft1.setVisible(true);
				shakeMinionLeft1.animate(200, 0, new IAnimationListener() {

					@Override
					public void onAnimationStarted(
							AnimatedSprite pAnimatedSprite,
							int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationLoopFinished(
							AnimatedSprite pAnimatedSprite,
							int pRemainingLoopCount, int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFrameChanged(
							AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
							int pNewFrameIndex) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// TODO Auto-generated method stub
						shakeMinionLeft1.setVisible(false);
						shakeMinionLeft0.setVisible(false);
						shakeMinionLeft3.setVisible(false);
						shakeMinionLeft2.setVisible(true);
						shakeMinionLeft2.animate(200, 0,
								new IAnimationListener() {

									@Override
									public void onAnimationStarted(
											AnimatedSprite pAnimatedSprite,
											int pInitialLoopCount) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationLoopFinished(
											AnimatedSprite pAnimatedSprite,
											int pRemainingLoopCount,
											int pInitialLoopCount) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationFrameChanged(
											AnimatedSprite pAnimatedSprite,
											int pOldFrameIndex,
											int pNewFrameIndex) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onAnimationFinished(
											AnimatedSprite pAnimatedSprite) {
										// TODO Auto-generated method stub
										shakeMinionLeft1.setVisible(false);
										shakeMinionLeft2.setVisible(false);
										shakeMinionLeft0.setVisible(false);
										shakeMinionLeft3.setVisible(true);
										shakeMinionLeft3.animate(200, 0,
												new IAnimationListener() {

													@Override
													public void onAnimationStarted(
															AnimatedSprite pAnimatedSprite,
															int pInitialLoopCount) {
														// TODO Auto-generated
														// method stub

													}

													@Override
													public void onAnimationLoopFinished(
															AnimatedSprite pAnimatedSprite,
															int pRemainingLoopCount,
															int pInitialLoopCount) {
														// TODO Auto-generated
														// method stub

													}

													@Override
													public void onAnimationFrameChanged(
															AnimatedSprite pAnimatedSprite,
															int pOldFrameIndex,
															int pNewFrameIndex) {
														// TODO Auto-generated
														// method stub

													}

													@Override
													public void onAnimationFinished(
															AnimatedSprite pAnimatedSprite) {
														// TODO Auto-generated
														// method stub
														if (isMinionFrightened) {
															isMinionFrightened = false;
														}
														if ((hitMinionEntity
																.isVisible() == false)
																&& (laughMinionEntity
																		.isVisible() == false)
																&& (shakeMinionRightEntity
																		.isVisible() == false)
																&& (hornMinionEntity
																		.isVisible() == false)
																&& (!fireMinionEntity
																		.isVisible())
																&& (!mirrorBrokeEntity
																		.isVisible())) {
															idleMinionEntity
																	.setVisible(true);
														}
														shakeMinionLeftEntity
																.setVisible(false);
														if (mMinionFrightenedSound
																.isLoaded()) {
															mMinionFrightenedSound
																	.stop();
														}
													}
												});
									}
								});
					}
				});
			}
		});
	}

	public void animationSpeakSprite() {
		speakMinionEntity.setVisible(true);
		speakMinion1.setVisible(false);
		speakMinion2.setVisible(false);
		speakMinion0.setVisible(true);
		speakMinion0.animate(200, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				speakMinion0.setVisible(false);
				speakMinion2.setVisible(false);
				speakMinion1.setVisible(true);
				speakMinion1.animate(200, 0, new IAnimationListener() {

					@Override
					public void onAnimationStarted(
							AnimatedSprite pAnimatedSprite,
							int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationLoopFinished(
							AnimatedSprite pAnimatedSprite,
							int pRemainingLoopCount, int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFrameChanged(
							AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
							int pNewFrameIndex) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// TODO Auto-generated method stub
						speakMinion1.setVisible(false);
						speakMinion0.setVisible(false);
						speakMinion2.setVisible(true);
						speakMinion2.animate(200, 0, new IAnimationListener() {

							@Override
							public void onAnimationStarted(
									AnimatedSprite pAnimatedSprite,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationLoopFinished(
									AnimatedSprite pAnimatedSprite,
									int pRemainingLoopCount,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFrameChanged(
									AnimatedSprite pAnimatedSprite,
									int pOldFrameIndex, int pNewFrameIndex) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFinished(
									AnimatedSprite pAnimatedSprite) {
								// TODO Auto-generated method stub
								speakMinionEntity.setVisible(false);
							}
						});
					}
				});
			}
		});
	}

	public void animateHornSprite() {
		hornMinionEntity.setVisible(true);

		minionHorn1.setVisible(false);
		minionHorn2.setVisible(false);
		minionHorn3.setVisible(false);
		minionHorn4.setVisible(false);
		minionHorn5.setVisible(false);
		minionHorn0.setVisible(true);
		minionHorn0.animate(200, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				minionHorn0.setVisible(false);
				minionHorn2.setVisible(false);
				minionHorn3.setVisible(false);
				minionHorn4.setVisible(false);
				minionHorn5.setVisible(false);
				minionHorn1.setVisible(true);
				if (hornMinionEntity.isVisible()) {
					if (mMinionHornSound.isLoaded()) {
						mMinionHornSound.stop();
						mMinionHornSound.play();
					}
				}
				minionHorn1.animate(150, 0, new IAnimationListener() {

					@Override
					public void onAnimationStarted(
							AnimatedSprite pAnimatedSprite,
							int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationLoopFinished(
							AnimatedSprite pAnimatedSprite,
							int pRemainingLoopCount, int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFrameChanged(
							AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
							int pNewFrameIndex) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// TODO Auto-generated method stub
						minionHorn1.setVisible(false);
						minionHorn0.setVisible(false);
						minionHorn3.setVisible(false);
						minionHorn4.setVisible(false);
						minionHorn5.setVisible(false);
						minionHorn2.setVisible(true);
						minionHorn2.animate(150, 0, new IAnimationListener() {

							@Override
							public void onAnimationStarted(
									AnimatedSprite pAnimatedSprite,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationLoopFinished(
									AnimatedSprite pAnimatedSprite,
									int pRemainingLoopCount,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFrameChanged(
									AnimatedSprite pAnimatedSprite,
									int pOldFrameIndex, int pNewFrameIndex) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFinished(
									AnimatedSprite pAnimatedSprite) {
								// TODO Auto-generated method stub
								minionHorn1.setVisible(false);
								minionHorn2.setVisible(false);
								minionHorn0.setVisible(false);
								minionHorn4.setVisible(false);
								minionHorn5.setVisible(false);
								minionHorn3.setVisible(true);
								minionHorn3.animate(150, 0,
										new IAnimationListener() {

											@Override
											public void onAnimationStarted(
													AnimatedSprite pAnimatedSprite,
													int pInitialLoopCount) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onAnimationLoopFinished(
													AnimatedSprite pAnimatedSprite,
													int pRemainingLoopCount,
													int pInitialLoopCount) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onAnimationFrameChanged(
													AnimatedSprite pAnimatedSprite,
													int pOldFrameIndex,
													int pNewFrameIndex) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onAnimationFinished(
													AnimatedSprite pAnimatedSprite) {
												// TODO Auto-generated method
												// stub
												minionHorn1.setVisible(false);
												minionHorn2.setVisible(false);
												minionHorn3.setVisible(false);
												minionHorn0.setVisible(false);
												minionHorn5.setVisible(false);
												minionHorn4.setVisible(true);
												minionHorn4
														.animate(
																150,
																0,
																new IAnimationListener() {

																	@Override
																	public void onAnimationStarted(
																			AnimatedSprite pAnimatedSprite,
																			int pInitialLoopCount) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																	}

																	@Override
																	public void onAnimationLoopFinished(
																			AnimatedSprite pAnimatedSprite,
																			int pRemainingLoopCount,
																			int pInitialLoopCount) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																	}

																	@Override
																	public void onAnimationFrameChanged(
																			AnimatedSprite pAnimatedSprite,
																			int pOldFrameIndex,
																			int pNewFrameIndex) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																	}

																	@Override
																	public void onAnimationFinished(
																			AnimatedSprite pAnimatedSprite) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub
																		minionHorn1
																				.setVisible(false);
																		minionHorn2
																				.setVisible(false);
																		minionHorn3
																				.setVisible(false);
																		minionHorn4
																				.setVisible(false);
																		minionHorn0
																				.setVisible(false);

																		minionHorn5
																				.setVisible(true);
																		minionHorn5
																				.animate(
																						150,
																						0,
																						new IAnimationListener() {

																							@Override
																							public void onAnimationStarted(
																									AnimatedSprite pAnimatedSprite,
																									int pInitialLoopCount) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub

																							}

																							@Override
																							public void onAnimationLoopFinished(
																									AnimatedSprite pAnimatedSprite,
																									int pRemainingLoopCount,
																									int pInitialLoopCount) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub

																							}

																							@Override
																							public void onAnimationFrameChanged(
																									AnimatedSprite pAnimatedSprite,
																									int pOldFrameIndex,
																									int pNewFrameIndex) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub

																							}

																							@Override
																							public void onAnimationFinished(
																									AnimatedSprite pAnimatedSprite) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub
																								hornMinionEntity
																										.setVisible(false);
																								if (mMinionHornSound
																										.isLoaded()) {
																									mMinionHornSound
																											.stop();
																								}
																								if (isPlayingHorn) {
																									isPlayingHorn = false;

																								}
																								if ((hitMinionEntity
																										.isVisible() == false)
																										&& (laughMinionEntity
																												.isVisible() == false)
																										&& (shakeMinionLeftEntity
																												.isVisible() == false)
																										&& (shakeMinionRightEntity
																												.isVisible() == false)
																										&& !hornMinionEntity
																												.isVisible()
																										&& (!fireMinionEntity
																												.isVisible())
																										&& (!mirrorBrokeEntity
																												.isVisible())) {
																									idleMinionEntity
																											.setVisible(true);

																								}

																							}
																						});
																	}
																});
											}
										});
							}
						});
					}
				});
			}
		});
	}

	public void animateLaughingSprite() {
		laughMinionEntity.setVisible(true);
		minionLaugh1.setVisible(false);
		minionLaugh3.setVisible(false);
		minionLaugh4.setVisible(false);
		minionLaugh5.setVisible(false);
		minionLaugh6.setVisible(false);
		minionLaugh7.setVisible(false);
		minionLaugh0.setVisible(true);
		if (mMinionLaugh.isLoaded()) {
			mMinionLaugh.stop();
			mMinionLaugh.play();
		}

		minionLaugh0.animate(150, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				minionLaugh0.setVisible(false);
				minionLaugh3.setVisible(false);
				minionLaugh4.setVisible(false);
				minionLaugh5.setVisible(false);
				minionLaugh6.setVisible(false);
				minionLaugh7.setVisible(false);
				minionLaugh1.setVisible(true);
				minionLaugh1.animate(150, 0, new IAnimationListener() {

					@Override
					public void onAnimationStarted(
							AnimatedSprite pAnimatedSprite,
							int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationLoopFinished(
							AnimatedSprite pAnimatedSprite,
							int pRemainingLoopCount, int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFrameChanged(
							AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
							int pNewFrameIndex) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// TODO Auto-generated method stub
						minionLaugh1.setVisible(false);
						minionLaugh0.setVisible(false);
						minionLaugh4.setVisible(false);
						minionLaugh5.setVisible(false);
						minionLaugh6.setVisible(false);
						minionLaugh7.setVisible(false);
						minionLaugh3.setVisible(true);
						minionLaugh3.animate(150, 0, new IAnimationListener() {

							@Override
							public void onAnimationStarted(
									AnimatedSprite pAnimatedSprite,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationLoopFinished(
									AnimatedSprite pAnimatedSprite,
									int pRemainingLoopCount,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFrameChanged(
									AnimatedSprite pAnimatedSprite,
									int pOldFrameIndex, int pNewFrameIndex) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFinished(
									AnimatedSprite pAnimatedSprite) {
								// TODO Auto-generated method stub
								minionLaugh1.setVisible(false);
								minionLaugh3.setVisible(false);
								minionLaugh0.setVisible(false);
								minionLaugh5.setVisible(false);
								minionLaugh6.setVisible(false);
								minionLaugh7.setVisible(false);
								minionLaugh4.setVisible(true);
								minionLaugh4.animate(150, 0,
										new IAnimationListener() {

											@Override
											public void onAnimationStarted(
													AnimatedSprite pAnimatedSprite,
													int pInitialLoopCount) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onAnimationLoopFinished(
													AnimatedSprite pAnimatedSprite,
													int pRemainingLoopCount,
													int pInitialLoopCount) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onAnimationFrameChanged(
													AnimatedSprite pAnimatedSprite,
													int pOldFrameIndex,
													int pNewFrameIndex) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onAnimationFinished(
													AnimatedSprite pAnimatedSprite) {
												// TODO Auto-generated method
												// stub
												minionLaugh1.setVisible(false);
												minionLaugh3.setVisible(false);
												minionLaugh4.setVisible(false);
												minionLaugh0.setVisible(false);
												minionLaugh6.setVisible(false);
												minionLaugh7.setVisible(false);
												minionLaugh5.setVisible(true);
												minionLaugh5
														.animate(
																150,
																0,
																new IAnimationListener() {

																	@Override
																	public void onAnimationStarted(
																			AnimatedSprite pAnimatedSprite,
																			int pInitialLoopCount) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																	}

																	@Override
																	public void onAnimationLoopFinished(
																			AnimatedSprite pAnimatedSprite,
																			int pRemainingLoopCount,
																			int pInitialLoopCount) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																	}

																	@Override
																	public void onAnimationFrameChanged(
																			AnimatedSprite pAnimatedSprite,
																			int pOldFrameIndex,
																			int pNewFrameIndex) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																	}

																	@Override
																	public void onAnimationFinished(
																			AnimatedSprite pAnimatedSprite) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub
																		minionLaugh1
																				.setVisible(false);
																		minionLaugh3
																				.setVisible(false);
																		minionLaugh4
																				.setVisible(false);
																		minionLaugh5
																				.setVisible(false);
																		minionLaugh0
																				.setVisible(false);
																		minionLaugh7
																				.setVisible(false);
																		minionLaugh6
																				.setVisible(true);
																		minionLaugh6
																				.animate(
																						150,
																						0,
																						new IAnimationListener() {

																							@Override
																							public void onAnimationStarted(
																									AnimatedSprite pAnimatedSprite,
																									int pInitialLoopCount) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub

																							}

																							@Override
																							public void onAnimationLoopFinished(
																									AnimatedSprite pAnimatedSprite,
																									int pRemainingLoopCount,
																									int pInitialLoopCount) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub

																							}

																							@Override
																							public void onAnimationFrameChanged(
																									AnimatedSprite pAnimatedSprite,
																									int pOldFrameIndex,
																									int pNewFrameIndex) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub

																							}

																							@Override
																							public void onAnimationFinished(
																									AnimatedSprite pAnimatedSprite) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub
																								minionLaugh1
																										.setVisible(false);
																								minionLaugh3
																										.setVisible(false);
																								minionLaugh4
																										.setVisible(false);
																								minionLaugh5
																										.setVisible(false);
																								minionLaugh6
																										.setVisible(false);
																								minionLaugh0
																										.setVisible(false);
																								minionLaugh7
																										.setVisible(true);
																								minionLaugh7
																										.animate(
																												150,
																												0,
																												new IAnimationListener() {

																													@Override
																													public void onAnimationStarted(
																															AnimatedSprite pAnimatedSprite,
																															int pInitialLoopCount) {
																														// TODO
																														// Auto-generated
																														// method
																														// stub

																													}

																													@Override
																													public void onAnimationLoopFinished(
																															AnimatedSprite pAnimatedSprite,
																															int pRemainingLoopCount,
																															int pInitialLoopCount) {
																														// TODO
																														// Auto-generated
																														// method
																														// stub

																													}

																													@Override
																													public void onAnimationFrameChanged(
																															AnimatedSprite pAnimatedSprite,
																															int pOldFrameIndex,
																															int pNewFrameIndex) {
																														// TODO
																														// Auto-generated
																														// method
																														// stub

																													}

																													@Override
																													public void onAnimationFinished(
																															AnimatedSprite pAnimatedSprite) {
																														// TODO
																														// Auto-generated
																														// method
																														// stub
																														laughMinionEntity
																																.setVisible(false);

																														if (mMinionLaugh
																																.isLoaded()) {
																															mMinionLaugh
																																	.stop();
																														}
																														if (isLaughing) {
																															isLaughing = false;
																														}

																														if ((hitMinionEntity
																																.isVisible() == false)
																																&& (hornMinionEntity
																																		.isVisible() == false)
																																&& (shakeMinionLeftEntity
																																		.isVisible() == false)
																																&& (shakeMinionRightEntity
																																		.isVisible() == false)
																																&& (!mirrorBrokeEntity
																																		.isVisible())
																																&& (!fireMinionEntity
																																		.isVisible())) {
																															idleMinionEntity
																																	.setVisible(true);
																															/*
																															 * if
																															 * (
																															 * mIdleMinionSound
																															 * .
																															 * isLoaded
																															 * (
																															 * )
																															 * )
																															 * {
																															 * mIdleMinionSound
																															 * .
																															 * play
																															 * (
																															 * )
																															 * ;
																															 * }
																															 */
																														}
																													}
																												});
																							}
																						});
																	}
																});
											}
										});
							}
						});
					}
				});
			}
		});
	}

	public void animateIdleSprite() {
		//
		if (splashImage.getVisibility() == View.VISIBLE) {
			splashImage.setVisibility(View.INVISIBLE);
		}
		idleMinion0.setVisible(true);
		idleMinion1.setVisible(false);
		idleMinion2.setVisible(false);
		idleMinion3.setVisible(false);
		idleMinion4.setVisible(false);
		idleMinion5.setVisible(false);
		idleMinion6.setVisible(false);
		idleMinion7.setVisible(false);
		idleMinion8.setVisible(false);
		idleMinion9.setVisible(false);
		idleMinion10.setVisible(false);
		idleMinion0.animate(180, 0, new IAnimationListener() {

			@Override
			public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
					int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
					int pRemainingLoopCount, int pInitialLoopCount) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
					int pOldFrameIndex, int pNewFrameIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
				// TODO Auto-generated method stub
				idleMinion1.setVisible(true);
				idleMinion0.setVisible(false);
				idleMinion2.setVisible(false);
				idleMinion3.setVisible(false);
				idleMinion4.setVisible(false);
				idleMinion5.setVisible(false);
				idleMinion6.setVisible(false);
				idleMinion7.setVisible(false);
				idleMinion8.setVisible(false);
				idleMinion9.setVisible(false);
				idleMinion10.setVisible(false);
				idleMinion1.animate(180, 0, new IAnimationListener() {

					@Override
					public void onAnimationStarted(
							AnimatedSprite pAnimatedSprite,
							int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationLoopFinished(
							AnimatedSprite pAnimatedSprite,
							int pRemainingLoopCount, int pInitialLoopCount) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFrameChanged(
							AnimatedSprite pAnimatedSprite, int pOldFrameIndex,
							int pNewFrameIndex) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationFinished(
							AnimatedSprite pAnimatedSprite) {
						// TODO Auto-generated method stub
						idleMinion1.setVisible(false);
						idleMinion3.setVisible(false);
						idleMinion4.setVisible(false);
						idleMinion5.setVisible(false);
						idleMinion6.setVisible(false);
						idleMinion7.setVisible(false);
						idleMinion8.setVisible(false);
						idleMinion9.setVisible(false);
						idleMinion10.setVisible(false);
						idleMinion2.setVisible(true);
						idleMinion2.animate(180, 0, new IAnimationListener() {

							@Override
							public void onAnimationStarted(
									AnimatedSprite pAnimatedSprite,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationLoopFinished(
									AnimatedSprite pAnimatedSprite,
									int pRemainingLoopCount,
									int pInitialLoopCount) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFrameChanged(
									AnimatedSprite pAnimatedSprite,
									int pOldFrameIndex, int pNewFrameIndex) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationFinished(
									AnimatedSprite pAnimatedSprite) {
								// TODO Auto-generated method stub
								idleMinion1.setVisible(false);
								idleMinion2.setVisible(false);
								idleMinion0.setVisible(false);
								idleMinion4.setVisible(false);
								idleMinion5.setVisible(false);
								idleMinion6.setVisible(false);
								idleMinion7.setVisible(false);
								idleMinion8.setVisible(false);
								idleMinion9.setVisible(false);
								idleMinion10.setVisible(false);
								idleMinion3.setVisible(true);
								idleMinion3.animate(180, 0,
										new IAnimationListener() {

											@Override
											public void onAnimationStarted(
													AnimatedSprite pAnimatedSprite,
													int pInitialLoopCount) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onAnimationLoopFinished(
													AnimatedSprite pAnimatedSprite,
													int pRemainingLoopCount,
													int pInitialLoopCount) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onAnimationFrameChanged(
													AnimatedSprite pAnimatedSprite,
													int pOldFrameIndex,
													int pNewFrameIndex) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onAnimationFinished(
													AnimatedSprite pAnimatedSprite) {
												// TODO Auto-generated method
												// stub
												idleMinion1.setVisible(false);
												idleMinion2.setVisible(false);
												idleMinion0.setVisible(false);
												idleMinion3.setVisible(false);
												idleMinion5.setVisible(false);
												idleMinion6.setVisible(false);
												idleMinion7.setVisible(false);
												idleMinion8.setVisible(false);
												idleMinion9.setVisible(false);
												idleMinion10.setVisible(false);
												idleMinion4.setVisible(true);
												/*
												 * if (idleMinionEntity
												 * .isVisible()) { //
												 * isPlayingHello = true; if
												 * (mMinionHelloSound
												 * .isLoaded()) {
												 * mMinionHornSound.stop();
												 * mMinionHelloSound .play(); }
												 * }
												 */
												idleMinion4
														.animate(
																180,
																0,
																new IAnimationListener() {

																	@Override
																	public void onAnimationStarted(
																			AnimatedSprite pAnimatedSprite,
																			int pInitialLoopCount) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																	}

																	@Override
																	public void onAnimationLoopFinished(
																			AnimatedSprite pAnimatedSprite,
																			int pRemainingLoopCount,
																			int pInitialLoopCount) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																	}

																	@Override
																	public void onAnimationFrameChanged(
																			AnimatedSprite pAnimatedSprite,
																			int pOldFrameIndex,
																			int pNewFrameIndex) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																	}

																	@Override
																	public void onAnimationFinished(
																			AnimatedSprite pAnimatedSprite) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub

																		idleMinion5
																				.animate(
																						180,
																						0,
																						new IAnimationListener() {

																							@Override
																							public void onAnimationStarted(
																									AnimatedSprite pAnimatedSprite,
																									int pInitialLoopCount) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub

																							}

																							@Override
																							public void onAnimationLoopFinished(
																									AnimatedSprite pAnimatedSprite,
																									int pRemainingLoopCount,
																									int pInitialLoopCount) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub

																							}

																							@Override
																							public void onAnimationFrameChanged(
																									AnimatedSprite pAnimatedSprite,
																									int pOldFrameIndex,
																									int pNewFrameIndex) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub

																							}

																							@Override
																							public void onAnimationFinished(
																									AnimatedSprite pAnimatedSprite) {
																								// TODO
																								// Auto-generated
																								// method
																								// stub
																								idleMinion1
																										.setVisible(false);
																								idleMinion2
																										.setVisible(false);
																								idleMinion0
																										.setVisible(false);
																								idleMinion3
																										.setVisible(false);
																								idleMinion4
																										.setVisible(false);
																								idleMinion5
																										.setVisible(false);
																								idleMinion7
																										.setVisible(false);
																								idleMinion8
																										.setVisible(false);
																								idleMinion9
																										.setVisible(false);
																								idleMinion10
																										.setVisible(false);
																								idleMinion6
																										.setVisible(true);
																								idleMinion6
																										.animate(
																												180,
																												0,
																												new IAnimationListener() {

																													@Override
																													public void onAnimationStarted(
																															AnimatedSprite pAnimatedSprite,
																															int pInitialLoopCount) {
																														// TODO
																														// Auto-generated
																														// method
																														// stub

																													}

																													@Override
																													public void onAnimationLoopFinished(
																															AnimatedSprite pAnimatedSprite,
																															int pRemainingLoopCount,
																															int pInitialLoopCount) {
																														// TODO
																														// Auto-generated
																														// method
																														// stub

																													}

																													@Override
																													public void onAnimationFrameChanged(
																															AnimatedSprite pAnimatedSprite,
																															int pOldFrameIndex,
																															int pNewFrameIndex) {
																														// TODO
																														// Auto-generated
																														// method
																														// stub

																													}

																													@Override
																													public void onAnimationFinished(
																															AnimatedSprite pAnimatedSprite) {
																														// TODO
																														// Auto-generated
																														// method
																														// stub
																														idleMinion1
																																.setVisible(false);
																														idleMinion2
																																.setVisible(false);
																														idleMinion0
																																.setVisible(false);
																														idleMinion3
																																.setVisible(false);
																														idleMinion4
																																.setVisible(false);
																														idleMinion5
																																.setVisible(false);
																														idleMinion6
																																.setVisible(false);
																														idleMinion8
																																.setVisible(false);
																														idleMinion9
																																.setVisible(false);
																														idleMinion10
																																.setVisible(false);
																												
																														idleMinion7
																																.setVisible(true);
																														idleMinion7
																																.animate(
																																		180,
																																		0,
																																		new IAnimationListener() {

																																			@Override
																																			public void onAnimationStarted(
																																					AnimatedSprite pAnimatedSprite,
																																					int pInitialLoopCount) {
																																				// TODO
																																				// Auto-generated
																																				// method
																																				// stub

																																			}

																																			@Override
																																			public void onAnimationLoopFinished(
																																					AnimatedSprite pAnimatedSprite,
																																					int pRemainingLoopCount,
																																					int pInitialLoopCount) {
																																				// TODO
																																				// Auto-generated
																																				// method
																																				// stub

																																			}

																																			@Override
																																			public void onAnimationFrameChanged(
																																					AnimatedSprite pAnimatedSprite,
																																					int pOldFrameIndex,
																																					int pNewFrameIndex) {
																																				// TODO
																																				// Auto-generated
																																				// method
																																				// stub

																																			}

																																			@Override
																																			public void onAnimationFinished(
																																					AnimatedSprite pAnimatedSprite) {
																																				// TODO
																																				// Auto-generated
																																				// method
																																				// stub
																																				idleMinion1
																																						.setVisible(false);
																																				idleMinion2
																																						.setVisible(false);
																																				idleMinion0
																																						.setVisible(false);
																																				idleMinion3
																																						.setVisible(false);
																																				idleMinion4
																																						.setVisible(false);
																																				idleMinion5
																																						.setVisible(false);
																																				idleMinion6
																																						.setVisible(false);
																																				idleMinion7
																																						.setVisible(false);
																																				idleMinion9
																																						.setVisible(false);
																																				idleMinion10
																																						.setVisible(false);
																																				idleMinion8
																																						.setVisible(true);
																																				idleMinion8
																																						.animate(
																																								180,
																																								0,
																																								new IAnimationListener() {

																																									@Override
																																									public void onAnimationStarted(
																																											AnimatedSprite pAnimatedSprite,
																																											int pInitialLoopCount) {
																																										// TODO
																																										// Auto-generated
																																										// method
																																										// stub

																																									}

																																									@Override
																																									public void onAnimationLoopFinished(
																																											AnimatedSprite pAnimatedSprite,
																																											int pRemainingLoopCount,
																																											int pInitialLoopCount) {
																																										// TODO
																																										// Auto-generated
																																										// method
																																										// stub

																																									}

																																									@Override
																																									public void onAnimationFrameChanged(
																																											AnimatedSprite pAnimatedSprite,
																																											int pOldFrameIndex,
																																											int pNewFrameIndex) {
																																										// TODO
																																										// Auto-generated
																																										// method
																																										// stub

																																									}

																																									@Override
																																									public void onAnimationFinished(
																																											AnimatedSprite pAnimatedSprite) {
																																										// TODO
																																										// Auto-generated
																																										// method
																																										// stub
																																										idleMinion1
																																												.setVisible(false);
																																										idleMinion2
																																												.setVisible(false);
																																										idleMinion0
																																												.setVisible(false);
																																										idleMinion3
																																												.setVisible(false);
																																										idleMinion4
																																												.setVisible(false);
																																										idleMinion5
																																												.setVisible(false);
																																										idleMinion6
																																												.setVisible(false);
																																										idleMinion7
																																												.setVisible(false);
																																										idleMinion8
																																												.setVisible(false);
																																										idleMinion10
																																												.setVisible(false);
																																										idleMinion9
																																												.setVisible(true);
																																										idleMinion9
																																												.animate(
																																														180,
																																														0,
																																														new IAnimationListener() {

																																															@Override
																																															public void onAnimationStarted(
																																																	AnimatedSprite pAnimatedSprite,
																																																	int pInitialLoopCount) {
																																																// TODO
																																																// Auto-generated
																																																// method
																																																// stub

																																															}

																																															@Override
																																															public void onAnimationLoopFinished(
																																																	AnimatedSprite pAnimatedSprite,
																																																	int pRemainingLoopCount,
																																																	int pInitialLoopCount) {
																																																// TODO
																																																// Auto-generated
																																																// method
																																																// stub

																																															}

																																															@Override
																																															public void onAnimationFrameChanged(
																																																	AnimatedSprite pAnimatedSprite,
																																																	int pOldFrameIndex,
																																																	int pNewFrameIndex) {
																																																// TODO
																																																// Auto-generated
																																																// method
																																																// stub

																																															}

																																															@Override
																																															public void onAnimationFinished(
																																																	AnimatedSprite pAnimatedSprite) {
																																																// TODO
																																																// Auto-generated
																																																// method
																																																// stub
																																																idleMinion1
																																																		.setVisible(false);
																																																idleMinion2
																																																		.setVisible(false);
																																																idleMinion0
																																																		.setVisible(false);
																																																idleMinion3
																																																		.setVisible(false);
																																																idleMinion4
																																																		.setVisible(false);
																																																idleMinion5
																																																		.setVisible(false);
																																																idleMinion6
																																																		.setVisible(false);
																																																idleMinion7
																																																		.setVisible(false);
																																																idleMinion8
																																																		.setVisible(false);
																																																idleMinion9
																																																		.setVisible(false);
																																																idleMinion10
																																																		.setVisible(true);
																																																idleMinion10
																																																		.animate(
																																																				180,
																																																				0,
																																																				new IAnimationListener() {

																																																					@Override
																																																					public void onAnimationStarted(
																																																							AnimatedSprite pAnimatedSprite,
																																																							int pInitialLoopCount) {
																																																						// TODO
																																																						// Auto-generated
																																																						// method
																																																						// stub

																																																					}

																																																					@Override
																																																					public void onAnimationLoopFinished(
																																																							AnimatedSprite pAnimatedSprite,
																																																							int pRemainingLoopCount,
																																																							int pInitialLoopCount) {
																																																						// TODO
																																																						// Auto-generated
																																																						// method
																																																						// stub

																																																					}

																																																					@Override
																																																					public void onAnimationFrameChanged(
																																																							AnimatedSprite pAnimatedSprite,
																																																							int pOldFrameIndex,
																																																							int pNewFrameIndex) {
																																																						// TODO
																																																						// Auto-generated
																																																						// method
																																																						// stub

																																																					}

																																																					@Override
																																																					public void onAnimationFinished(
																																																							AnimatedSprite pAnimatedSprite) {
																																																						// TODO
																																																						// Auto-generated
																																																						// method
																																																						idleMinion1
																																																						.setVisible(false);
																																																				idleMinion2
																																																						.setVisible(false);
																																																				idleMinion10
																																																						.setVisible(false);
																																																				idleMinion3
																																																						.setVisible(false);
																																																				idleMinion4
																																																						.setVisible(false);
																																																				idleMinion5
																																																						.setVisible(false);
																																																				idleMinion6
																																																						.setVisible(false);
																																																				idleMinion7
																																																						.setVisible(false);
																																																				idleMinion9
																																																						.setVisible(false);
																																																				idleMinion8
																																																						.setVisible(false);
																																																				idleMinion0
																																																						.setVisible(true);
																																																				isAnimating = false;
										
																																																			}
																																																				});
																																															}
																																														});
																																									}
																																								});
																																			}
																																		});
																													}
																												});
																							}
																						});
																	}
																});
											}
										});
							}
						});
					}
				});
			}
		});

	}

	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.activity_main;
	}

	@Override
	protected int getRenderSurfaceViewID() {
		// TODO Auto-generated method stub
		return R.id.gameSurfaceView;
	}

	@Override
	public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAccelerationChanged(AccelerationData pAccelerationData) {
		// TODO Auto-generated method stub
		long curTime = System.currentTimeMillis();
		// only allow one update every 100ms.
		if ((curTime - lastUpdate) > 300) {
			long diffTime = (curTime - lastUpdate);
			lastUpdate = curTime;

			x = pAccelerationData.getX();
			y = pAccelerationData.getY();
			z = pAccelerationData.getZ();

			if (Round(x, 4) > 9.0000) {
				Log.d("sensor", "X Right axis: " + x);
				// Toast.makeText(this, "Right shake detected",
				// Toast.LENGTH_SHORT).show();
				MainActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (idleMinionEntity.isVisible()) {
							idleMinionEntity.setVisible(false);
						}
						if (hitMinionEntity.isVisible()) {
							hitMinionEntity.setVisible(false);
						}
						if (laughMinionEntity.isVisible()) {
							laughMinionEntity.setVisible(false);
						}
						if (mMinionLaugh.isLoaded()) {
							mMinionLaugh.stop();
						}
						if (mMinionHornSound.isLoaded()) {
							mMinionHornSound.stop();
						}
						/*
						 * if (mMinionHelloSound.isLoaded()) {
						 * mMinionHelloSound.stop(); }
						 */

						if (mMinionScreamingSound.isLoaded()) {
							mMinionScreamingSound.stop();
						}
						if (mRPGSound.isLoaded()) {
							mRPGSound.stop();
						}
						if (mMirrorBrokenSound.isLoaded()) {
							mMirrorBrokenSound.stop();
						}

						if (hornMinionEntity.isVisible()) {
							hornMinionEntity.setVisible(false);
						}
						if (shakeMinionRightEntity.isVisible()) {
							shakeMinionRightEntity.setVisible(false);
						}
						if (mirrorBrokeEntity.isVisible()) {
							mirrorBrokeEntity.setVisible(false);
						}
						if (fireMinionEntity.isVisible()) {
							fireMinionEntity.setVisible(false);
						}
						if (speakMinionEntity.isVisible()) {
							speakMinionEntity.setVisible(false);
						}
						if (!minionHornButton.isVisible()) {
							minionHornButton.setVisible(true);
						}
						if (!mirrorBroke.isVisible()) {
							mirrorBroke.setVisible(true);
						}
						if (!fireMinionButton.isVisible()) {
							fireMinionButton.setVisible(true);
						}
						if (!minionLaughButton.isVisible()) {
							minionLaughButton.setVisible(true);
						}
						if (isLaughing) {
							isLaughing = false;
						}
						if (isPlayingHorn) {
							isPlayingHorn = false;
						}
						if (!isMinionFrightened) {
							isMinionFrightened = true;
						}
						/*
						 * if (isPlayingHello) { isPlayingHello = false; }
						 */
						if (isMinionHitted) {
							isMinionHitted = false;
						}
						if (isMinionFired) {
							isMinionFired = false;
						}
						if (isMirrorBroken) {
							isMirrorBroken = false;
						}
						shakeMinionLeftEntity.setVisible(true);
						animateShakeMinionLeft();
					}
				});
			} else if (Round(x, 4) < -9.0000) {
				Log.d("sensor", "X Left axis: " + x);
				// Toast.makeText(this,
				// "Left shake detected",Toast.LENGTH_SHORT).show();
				MainActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (mMinionLaugh.isLoaded()) {
							mMinionLaugh.stop();
						}
						if (mMinionHornSound.isLoaded()) {
							mMinionHornSound.stop();
						}
						/*
						 * if (mMinionHelloSound.isLoaded()) {
						 * mMinionHelloSound.stop(); }
						 */
						if (mMinionScreamingSound.isLoaded()) {
							mMinionScreamingSound.stop();
						}
						if (mRPGSound.isLoaded()) {
							mRPGSound.stop();
						}
						if (mMirrorBrokenSound.isLoaded()) {
							mMirrorBrokenSound.stop();
						}
						if (speakMinionEntity.isVisible()) {
							speakMinionEntity.setVisible(false);
						}

						if (idleMinionEntity.isVisible()) {
							idleMinionEntity.setVisible(false);
						}
						if (hitMinionEntity.isVisible()) {
							hitMinionEntity.setVisible(false);
						}
						if (laughMinionEntity.isVisible()) {
							laughMinionEntity.setVisible(false);
						}
						if (hornMinionEntity.isVisible()) {
							hornMinionEntity.setVisible(false);
						}
						if (shakeMinionLeftEntity.isVisible()) {
							shakeMinionLeftEntity.setVisible(false);
						}
						if (mirrorBrokeEntity.isVisible()) {
							mirrorBrokeEntity.setVisible(false);
						}
						if (fireMinionEntity.isVisible()) {
							fireMinionEntity.setVisible(false);
						}
						if (isMinionFired) {
							isMinionFired = false;
						}
						if (isMirrorBroken) {
							isMirrorBroken = false;
						}

						if (isLaughing) {
							isLaughing = false;
						}
						if (isPlayingHorn) {
							isPlayingHorn = false;
						}
						if (!isMinionFrightened) {
							isMinionFrightened = true;
						}
						/*
						 * if (isPlayingHello) { isPlayingHello = false; }
						 */
						if (isMinionHitted) {
							isMinionHitted = false;
						}
						shakeMinionRightEntity.setVisible(true);
						animateShakeMinionRight();
					}
				});
			}

			float speed = Math.abs(x + y + z - last_x - last_y - last_z)
					/ diffTime * 10000;

			// Log.d("sensor", "diff: " + diffTime + " - speed: " + speed);
			if (speed > SHAKE_THRESHOLD) {
				// Log.d("sensor", "shake detected w/ speed: " + speed);
				// Toast.makeText(this, "shake detected w/ speed: " + speed,
				// Toast.LENGTH_SHORT).show();
			}
			last_x = x;
			last_y = y;
			last_z = z;
		}
	}

	public static float Round(float Rval, int Rpl) {
		float p = (float) Math.pow(10, Rpl);
		Rval = Rval * p;
		float tmp = Math.round(Rval);
		return (float) tmp / p;
	}

	public void Start() {
		isListening = true;
		// Initialize minimum buffer size in bytes.
		minBufferSizeInBytes = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
				CHANNEL_CONFIG, AUDIO_FORMAT);
		maxJitter = AudioTrack.getMinBufferSize(SAMPLE_RATE_IN_HZ,
				AudioFormat.CHANNEL_OUT_MONO, AUDIO_FORMAT);
		track = new AudioTrack(AudioManager.STREAM_MUSIC, SAMPLE_RATE_IN_HZ,
				AudioFormat.CHANNEL_OUT_MONO, AUDIO_FORMAT, maxJitter,
				AudioTrack.MODE_STREAM);
		if (minBufferSizeInBytes == AudioRecord.ERROR_BAD_VALUE)
			Log.e(TAG,
					"Bad Value for \"minBufferSize\", recording parameters are not supported by the hardware");

		if (minBufferSizeInBytes == AudioRecord.ERROR)
			Log.e(TAG,
					"Bad Value for \"minBufferSize\", implementation was unable to query the hardware for its output properties");

		// Initialize Audio Recorder.
		try {
			audioRecorder = new AudioRecord(AUDIO_SOURCE, SAMPLE_RATE_IN_HZ,
					CHANNEL_CONFIG, AUDIO_FORMAT, minBufferSizeInBytes);
		} catch (IllegalArgumentException ex) {
			Log.e(TAG, "Illegal Arguments: " + ex.getMessage());
		}
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				audioRecorder.startRecording();

				int numberOfBytesRead = 0;
				byte audioBuffer[] = new byte[minBufferSizeInBytes];
				float tempBuffer[] = new float[CHECK_BLOCK_COUNT];
				int tempIndex = 0;
				boolean isRecording = false;
				int totalReadBytes = 0;
				byte totalByteBuffer[] = new byte[60 * 44100 * 2];

				// While data coming from microphone.
				while (true) {
					
			
					float totalAbsValue = 0.0f;
					short sample = 0;
							numberOfBytesRead = audioRecorder.read(audioBuffer, 0, minBufferSizeInBytes);
				
					// Analyze income sound.
					for (int i = 0; i < minBufferSizeInBytes; i += 2) {
						sample = (short) ((audioBuffer[i]) | audioBuffer[i + 1] << 8);
						totalAbsValue += Math.abs(sample)
								/ (numberOfBytesRead / 2);
					
					}
					// Set Animation of microphone.
					// handler.sendEmptyMessage((int)totalAbsValue);

					// Analyze tempBuffer.
					tempBuffer[tempIndex % CHECK_BLOCK_COUNT] = totalAbsValue;
					float tempBufferTotalCount = 0.0f;
					for (int i = 0; i < CHECK_BLOCK_COUNT; ++i) {
						tempBufferTotalCount += tempBuffer[i];
						
					}
					// Finalize value.
					tempBufferTotalCount = tempBufferTotalCount
							/ CHECK_BLOCK_COUNT;
					
					// Waiting for load speak to start recording.
					if ((tempBufferTotalCount >= 0 && tempBufferTotalCount <= START_RECORD_FROM)
							&& !isRecording) {

						Log.i("TAG", "Waiting for voice to start record.");
						tempIndex++;
						// changeTexthandler.sendEmptyMessage(0);
						continue;
					}
					if(isMinionFired || isMirrorBroken || isMinionHitted || isLaughing || isPlayingHorn || isMinionFrightened) {
						Log.i("TAG", "Soundddddddddddddddddddd   playiiiiiiiiiiinnnnnnnggggg");
						audioRecorder.stop();
						audioRecorder.release();
						audioRecorder = null;
						track.release();
						totalReadBytes = 0;
						isListening = false;
						break;
					 }
					
					if (tempBufferTotalCount > START_RECORD_FROM
							&& !isRecording) {
						Log.i("TAG", "Recording");
						// changeTexthandler.sendEmptyMessage(1);
						Log.i("TAG", "Recording Started Minion.");
						isRecording = true;
					}
             
					// Stop Recording and save data to file.
					if ((tempBufferTotalCount >= 0 && tempBufferTotalCount <= START_RECORD_FROM)
							&& isRecording) {
						Log.i("TAG", "Stop Recording and Save data to file");

						audioRecorder.stop();
						audioRecorder.release();
						audioRecorder = null;
						MainActivity.this.runOnUiThread(new Runnable() {
							@Override
							public void run() {

								idleMinionEntity.setVisible(false);
								if (!speakMinionEntity.isVisible()) {
									isPlaying = true;
									speakMinionEntity.setVisible(true);
									animationSpeakSprite();
								}
							}
						});
						track.setPlaybackRate(14000);
						track.play();
						track.write(totalByteBuffer, 0, totalReadBytes);
						track.release();
						totalReadBytes = 0;

						tempIndex++;
						// changeTexthandler.sendEmptyMessage(2);

						if (speakMinionEntity.isVisible()) {
							speakMinionEntity.setVisible(false);
						}
						isPlaying = false;
						isListening = false;
						if ((idleMinionEntity.isVisible() == false)
								&& (laughMinionEntity.isVisible() == false)
								&& (hornMinionEntity.isVisible() == false)
								&& (shakeMinionLeftEntity.isVisible() == false)
								&& (shakeMinionRightEntity.isVisible() == false)
								&& !fireMinionEntity.isVisible()
								&& !mirrorBrokeEntity.isVisible()
								&& !hitMinionEntity.isVisible()) {
							idleMinionEntity.setVisible(true);

						}
						break;

					}	
					// Record Sound.
					for (int i = 0; i < numberOfBytesRead; i++) 
						totalByteBuffer[totalReadBytes + i] = audioBuffer[i];
					totalReadBytes += numberOfBytesRead;
					tempIndex++;
					/////////////////////////////////////////////
					}
					

				return null;

			}
		}.execute();

	}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_BACK){
                    // do stuff
            	isBackKeyPressed = true;
            	
            }
            if(keyCode == KeyEvent.KEYCODE_HOME) {
            	 Log.d(TAG,"onMenu Button Back ButtonBack ButtonBack ButtonBack ButtonBack ButtonGame  Talking Minion() called");
                  isHomeKeyPressed = true;
            }
            return super.onKeyDown(keyCode, event);
    }

	@Override
	public void onAdClicked(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAdClosed(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAdOpened(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onApplicationExit(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRenderFailed(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRendered(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onVideoCompleted(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean shouldDisplayAd(String arg0, FlurryAdType arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void spaceDidFailToReceiveAd(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void spaceDidReceiveAd(String arg0) {
		// called when the ad has been prepared, ad can be displayed:
		FlurryAds.displayAd(this, mAdSpaceName, mBanner);
	}

}
