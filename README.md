<br>
<div align="center">
  <a href="https://github.com/vxhjsd/ParadisePicture">
    <img src="https://github.com/vxhjsd/ParadisePicture/blob/minor/sample/src/main/assets/header_new.png"/>
  </a>
  </div>
  
  <p align="center">
  <img alt="Android" src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/></a>
  <img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/></a>
</br>

Lightweight, simple library that will help those who make photo editors in Sketchware (and maybe not only in Sketchware). If you want to improve the project or add something to it, go ahead.

## How to build it using Sketchware & MT Manager

1. Download source code
- There are two folders in the path `library\src\main\java\cleancode\paradise` , copy them to any convenient place

   ![tutor](https://github.com/vxhjsd/ParadisePicture/blob/minor/sample/src/main/assets/tutor_new.png)


2. Now create an empty project in Sketchware with package name `cleancode.paradise`
   > You can use any package name, but you'll have to change it in the classes too, so I don't recommend doing that
   
3. Go to your project's Java/Kotlin manager, create **tool** and **view** folders
- Add the classes from the folders you copied earlier, compile project

4. You now have **classes.dex** at `/.sketchware/mysc/XXX/bin/dex/`

   > XXX is number of project, for example 602
  
- Create a folder for the future library `/.sketchware/libs/local_libs/your_folder`
- Add classes.dex there
- Using MT Manager, convert the file (classes.dex -> classes.jar)

  > You can remove extra classes created by Sketchware, then only two folders will remain
  
- Create a **config** file containing the package name

### Source Code Map

| Class                       | Role                                   |
| ----------------------------|----------------------------------------|
| `ColorFilterGenerator`      | Helps to change the colors of an image |
| `ImageAdjuster`             | Same as previous                       |
| `VignetteView`              | Vignette effect                        |
| `NoiseView`                 | Noise effect                           |
| `StripesView`               | Stripes effect                         |

# How do I use ParadisePicture?

### Import the library classes you need

```java
import cleancode.paradise.tool.ColorFilterGenerator;
import cleancode.paradise.tool.ImageAdjuster;
import cleancode.paradise.view.NoiseView;
import cleancode.paradise.view.StripesView;
import cleancode.paradise.view.VignetteView;
```

### VignetteView

```xml
<cleancode.paradise.view.VignetteView
android:id="@+id/a"
android:layout_height="350dp"
android:layout_width="350dp"
android:background="@drawable/corn"/>
```

```java
yourView.setVignetteColor(int color);
yourView.setVignetteAlpha(int alpha);
```

### Stripes or Noise

> The classes are similar, so only the numbers can be different.

```xml
<cleancode.paradise.view.NoiseView
android:id="@+id/noise"
android:layout_height="350dp"
android:layout_width="350dp"
android:background="@drawable/corn"/>
```

```java
yourView.setOriginalResFromAsset(Context context, String fileName);
yourView.setOriginalResFromResource(Context context, int resId);
yourView.setEffectAlpha(int effectAlpha);
yourView.setNumberToRepeat(int numberToRepeat);
```

### ColorFilterGenerator

> Used as ColorFilter

```java
yourImage.setColorFilter(ColorFilterGenerator.adjustColor(int hue, int contrast, int brightness, int saturation));
```

### ImageAdjuster

> Used as Bitmap

```java
ImageView imageView = findViewById(R.id.imageView);
Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
float brightness = 50f;
float contrast = 1.5f;
float hue = 45f;
float saturation = 1.2f;
Bitmap adjustedBitmap = ImageAdjuster.adjustImage(bitmap, brightness, contrast, hue, saturation);
imageView.setImageBitmap(adjustedBitmap);
```

## License

![WTFPL](https://github.com/vxhjsd/ParadisePicture/blob/minor/sample/src/main/assets/wtfpl.png)

This project uses the [WTFPL license](http://www.wtfpl.net/)
(Do **W**hat **T**he **F**uck You Want To **P**ublic **L**icense)
