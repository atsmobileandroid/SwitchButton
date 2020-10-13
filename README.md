# SwitchButton
Strep 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.atsmobileandroid:SwitchButton:Tag'
	}
  
Step 3. Add the xmk view 

      <com.belal.switchbutton.SwitchControl
              android:id="@+id/switchButton"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              app:background_color="#818181"
              app:button_color="#fff"
              app:corner="4dp"
              app:buttons_names="@array/buttons" // here you create an array of buttons names you want to show
              app:button_text_color="#000"
              app:button_text_size="20sp" />
              
Step 4. Call change selection listener

            ((SwitchControl) findViewById(R.id.switchButton)).setOnSwitchControlChanged(new OnSwitchControlChanged() {
                @Override
                public void onChanged(int id) {
                    Toast.makeText(MainActivity.this, "Button number: " + (id + 1) + " clicked", Toast.LENGTH_SHORT).show();
                }
            });
