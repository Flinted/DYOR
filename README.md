# **Doppel**


#### What is it?
___
Doppel is a library for creating customsiable dynamic placeholder UIs from your resolved views at runtime.


#### Why does it exist?
___

Google advise us to use Placeholder UI in the Material guidelines but, usually, this involves maintaining a second layout and swapping it in and out programmatically.  As content changes Placeholders need to be updated to reflect the new content or look out of place. Doppel solves this whilst allowing us to easily make good looking placeholder UIs.   

#### How does it work?
___

Doppel basically works by hiding the content of Views and swapping out their background drawables based on it's settings. This is very simple but very powerful as it means you can pass Doppel any view tree, or trees, and it will create a Placeholder for it. 

Doppel is highly customisable.  Follow through this ReadMe for more information on how.

# Installation
---

> Install instructions will go here.

# Usage
___

### Out of the Box
The following examples are all in Kotlin.  Java examples will be added shortly.
___

```kotlin
val configuration = DoppelConfigurationBuilder(context).build()
val doppel = Doppel(configuration, viewToDoppel, anotherViewToDoppel)
doppel.on()
```

You can pass any number of views to a Doppel instance, it will process these views, and all of the child views in the tree, in line with the given configuration.

> N.B: You must always pass a configuration as the first argument.  More on configurations below.

#### Doppel Instance Options
____
Above we created a default Doppel instance with some views. There are a few options available specific to the actual Doppel instance.

##### Target specific views
---

```Kotlin
doppel.targetViewsById(R.id.id1, R.id.id2, R.id.id3)
```

By targeting set views Doppel will ignore all others, this is useful for targeting specific elements. 
 
> N.B: If you target a view that is not found in any of the views passed to Doppel it will be ignored.

##### Exclude specific views
---

```kotlin
// DoppelExclusion(excludeChildViews: boolean, vararg ids: Int)
val exclusion1 = DoppelExclusion(false, R.id.id1, R.id.id2)
val exclusion2 = DoppelExclusion(true, R.id.id3, R.id.id4)

//excludeViewsById(vararg exclusions: DoppelExclusion)
doppel.excludeViewsById(exclusion1, exclusion2)
```

Excluding is slightly more complex, by creating a DoppelExclusion you can say whether you want to exclude just the views associated with the given ids, or the views and all of their child views recursively. 
Again you can pass as many ids as you wish to an exclusion, and as many exclusions as you wish to excludeViewsById.

>N.B: By setting a DoppelExclusion excludeChildViews argument to true all views within the given view will be excluded. 

##### Override view dimensions
---

```kotlin
// DoppelOverride(overrideHeightDP: Int?, overrideWidthDP: Int?, vararg ids: Int)
val override1 = DoppelOverride(25, 50, R.id.id1, R.id.id2)
val override2 = DoppelOverride( 25, null, R.id.id3)

//addOverrides(vararg overrides: DoppelOverride)
doppel.addOverrides(override1, override2)
```

You can also set overrides for multiple view ids. These override the views dimensions when in a Doppel state to the given values in dp. 
If you pass null for either height or width the original value will be used for the equivalent dimension. When Doppel is turned off the original view dimensions are restored.

>N.B: The above methods can be combined, but logical rules apply, and the order of invocation matters.  
>e.g. Overriding and Excluding the same view will simply exclude it. 
>e.g. Excluding a View and it's children then targeting one of those children will result in the child view remaining excluded.


### DoppelConfigurationBuilder
---

This is the core of customising Doppel.  You can have as many configurations as you wish for different settings and Inject the appropriate one as desired.
DoppelConfigurations are created with a builder that has a number of options.

```kotlin
val builder = DoppelConfigurationBuilder(context)
val configuration = builder.build()
```

There are a number of methods on the builder to customise Doppel. All of the methods return the same builder instance and can be chained.

##### toDepth
___
```kotlin
//toDepth(depth: Int)
builder.toDepth(3)
```
This allows you to set the 'resolution' of doppel. Or how many layers it Doppels before hiding remaining child views in a Doppel state.

##### parentViewInclusive
___
```kotlin
//parentViewInclusive(included: boolean)
builder.parentViewInclusive(true)
```

This boolean toggle allows you to set whether Doppeling includes the top level Views passed in to Doppel. 
It is useful to set this to false if you are Doppeling an entire screen and want to exclude the top level.

N.B: Please note that whether included or excluded, the top level view is always layer 1, this can be relevant when working with depth. 

##### targetSpecificViewTypes
___
```kotlin
//targetSpecificViewTypes(vararg viewTypes: Class<T:View>)
builder.targetSpecificViewTypes(
        AppCompatImageView::class.java, 
        AppCompatTextView::class.java, 
        MyCustomViewClass::class,java
)
```

This function allows you to set the view types that Doppel will target,  you can target any class that extends View so custom views can be targeted also. 

##### excludeSpecificViewTypes
___
```kotlin
//excludeSpecificViewTypes(vararg viewTypes: Class<T:View>)
builder.excludeSpecificViewTypes(
        AppCompatImageView::class.java, 
        AppCompatTextView::class.java, 
        MyCustomViewClass::class,java
)
```

The same as above but exclusion, all other views except those listed will be targeted.
> N.B: If you apply both target and exclusion by View type, the last applied will be honoured.

##### withBackgroundProvider
---
```kotlin
//withBackgroundProvider(provider: DoppelBackgroundProvider)
builder.withBackgroundProvider(doppelBackgroundProvider)
```

This allows you to replace the default background provider with a customised, or entirely custom DoppelBackgroundProvider, more on them below.

##### Summary
___
All of the above builder methods can be combined and chained.  Just remember to call build() at the end to obtain your configuration.
```kotlin
DoppelConfigurationBuilder(context)
    .toDepth(4)
    .targetSpecificViewTypes(LinearLayouts::class.java, AppCompatImageViews::class.jave)
    .withBackgroundProvider(customProvider)
    .build()
```

So the above would give you a configuration telling Doppel to: 
+ Apply backgrounds from your custom provider. 
+ Only to target Linear Layouts and AppCompatImageViews. 
+ Only to doppel a depth of 4 layers before hiding any other child views.

### DoppelConfigurable Interface.
___

You can also make your own, fully custom Configuration and ignore the Builder completely.

```kotlin
interface DoppelConfigurable {
    var depth: Int
    var parentViewInclusive: Boolean
    var backgroundProvider: DoppelBackgroundProvider

    fun validate(view: View): Boolean 
}
```
Most of the above is fairly self explanatory,  the validate method is where you implement your own logic to tell Doppel whether a View should be Doppeled or not.  


### DoppelBackgroundProvider
___

Doppel comes with two DoppelBackgroundProviders as standard, or you can provide your own.

##### DoppelColorDrawablesProvider
---
```kotlin
// DoppelColorDrawablesProvider(colors: List<Int>)
val provider = DoppelColorDrawablesProvider(colorsList)
```
This is the default provider for Doppel. It can also be customised or replaced. The passed in list of colors is selected from by layer.
In the event that you have more layers set than entries in the given colorList Doppel will loop through the list again.

> N.B: There is a convenience class DoppelColors that has some simple color schemes on it, or you can create your own. 

##### DoppelViewTypeColorProvider
---
```kotlin
// DoppelViewTypeColor(context: Context, colorId: Int, vararg ids: Int)
val textViewColorHolder = DoppelViewTypeColor(
                            context, 
                            R.color.colorAccent, 
                            AppCompatTextView::class.java, 
                            AppCompatEditText::class.java
)
val imageViewColorHolder = DoppelViewTypeColor(
                            context, 
                            R.color.colorPrimary, 
                            AppCompatImageView::class.java
)

// DoppelViewTypeColorProvider(defaultColor: Int)
val primaryDark = ContextCompat.getColor(context, R.color.primaryDark)
val provider = DoppelViewTypeColorProvider(primaryDark)

//addViewTypeColors(vararg colorHolders: DoppelViewTypeColor)
provider.addViewTypeColors(textViewColorHolder, imageViewColorHolder)
```

This DoppelBackgroundProvider does not care about the layer a view is on, but instead targets views based on their type.
This DoppelBackgroundProvider is very effective when targeting specific view types in your configuration.

#### Shared Methods
---
Both of the default DoppelBackgroundProviders have some shared methods also.

 ##### setAnimationSpeed
 ---
 ```kotlin
 //setAnimationSpeed(speed: Long)
backgroundProvider.setAnimationSpeed(500)
```
This allows you to set the speed any animation runs at in a Doppel state.  Setting it to 0 disables animation.

##### setMinAlpha
---
```kotlin
//setMinAlpha(alpha: Float)
backgroundProvider.setMinAlpha(0.4f)
```
This allows you to set the minimum alpha reached for the animation of the default DoppelBackgroundProviders.
Can be set anywhere from 0.00...f - 1.0f 

##### setMaxAlpha
---
```kotlin
//setMaxAlpha(alpha: Float)
backgroundProvider.setMaxAlpha(0.9f)
```
This allows you to set the maximum alpha reached for the animation of the default DoppelBackgroundProviders.
Can be set anywhere from 0.00...f - 1.0f 

##### setCornerRadius
---
```kotlin
// setCornerRadius(context: Context, radiusDp: Int)
backgroundProvider.setCornerRadius(context, 30)
```
This allows you to choose the corner radii for all views in the Doppel. 

##### setShrinkage
---
```kotlin
//setShrinkage(context: Context, shrinkageDp: Int)
backgroundProvider.setShrinkage(context, 5)
```
Often there is no space between views if padding is used in preference of margin.  
Shrinkage allows you to slightly, or significantly, reduce a views size when in a Doppel state, so that views appear separate. 
> N.B:  Negative shrinkage increases view size in a Doppel state.

##### setStroke
---
```kotlin
//setStroke(contextL Context, thicknessDp: Int, color: Int)
backgroundProvider.setStroke(context, 3, R.color.blue5_doppel) 
```
This allows you to set a stroke around all views,  first argument is the stroke thickness in pixels, second is the id for the Color resource to use.

##### Summary
---
So for an example of a fully customised DoppelColorDrawablesProvider
```kotlin
val primaryColor = ContextCompat.getColor(context, R.color.colorPrimary)
val accentColor = ContextCompat.getColor(context, R.color.colorAccent)
val colorsList = listOf(primaryColor, accentColor)
val provider = DoppelColorDrawablesProvider(colorsList)
provider.setAnimationSpeed(1000)
provider.setMinAlpha(0.6)
provider.setMaxAlpha(0.95)
provider.setCornerRadius(context, 5)
provider.setShrinkage(context, 10)
provider.setStroke(context, 3, R.color.colorPrimaryDark)
```

So the above will give you backgrounds that:
+ Alternates between primary and accent color by layer.
+ Completes an animation every second.
+ Fades in and out from 60% to 95% alpha.
+ Has 5 pixel rounding on all corners.
+ Shrinks all views by 10 pixels in Doppel state
+ Has a 3 pixel thick primary dark stroke around all views.


## Using All of the Doppel components
___
So now we can join it all together:

```kotlin
val textViewColorHolder = DoppelViewTypeColor(
                            context, 
                            R.color.colorAccent, 
                            AppCompatTextView::class.java, 
                            AppCompatEditText::class.java
)
val imageViewColorHolder = DoppelViewTypeColor(
                            context, 
                            R.color.colorPrimary, 
                            AppCompatImageView::class.java
)
val primaryDark = ContextCompat.getColor(context, R.id.colorPrimaryDark)
val provider = DoppelViewTypeColorProvider(primaryDark)
provider.addViewTypeColors(textViewColorHolder, imageViewColorHolder)
provider.setShrinkage(context, 10)
provider.setCornerRadius(context, 10)

val configuration = DoppelConfigurationBuilder(context)
                        .withBackgroundProvider(provider)
                        .targetViewTypes(AppCompatTextView::class.java, AppCompatImageView::class.java)
                        .parentViewInclusive(true)
                        .build()
                        
val doppel = Doppel(configuration, view1, view2, view3)
val doppel2 = Doppel(configuration, view4, view5)
doppel2.excludeViewsById(DoppelExclude(true, R.id.id1, R.id.id2, R.id.id3))
doppel.on()
doppel2.on()
```

Here we have two Doppel instances targeting different views but using the same configuration. 

Configurations can be held onto and reused throughout your app as required. Meaning most of the time you will just be doing this:
```kotlin
val doppel = Doppel(injectedConfiguration, viewToDoppel)
doppel.on()
```
Doppel instances themselves by their very nature are associated with live views, and should be destroyed along with the views.

### Default Settings:
---
These are the default settings for Doppel.

##### ANIMATION:
+ Animation Speed: 1000ms
+ MinAlpha: 0.6f
+ MaxAlpha: 1.0f

##### STROKE:
+ Width: 0pixels
+ Color: Color.Transparent

##### VIEWS
+ Corners: 0f
+ Shrinkage: 3pixels
+ Targeting: All views.

##### COLORS:
+ DoppelBackgroundProvider: DoppelColorDrawableProvider
+ Colors: DoppelColors.GRAYS
 
 

