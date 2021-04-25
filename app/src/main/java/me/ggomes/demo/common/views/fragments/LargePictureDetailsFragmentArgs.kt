package me.ggomes.demo.common.views.fragments

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class LargePictureDetailsFragmentArgs(
  public val largeImageUrl: String = ""
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("largeImageUrl", this.largeImageUrl)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): LargePictureDetailsFragmentArgs {
      bundle.setClassLoader(LargePictureDetailsFragmentArgs::class.java.classLoader)
      val __largeImageUrl : String?
      if (bundle.containsKey("largeImageUrl")) {
        __largeImageUrl = bundle.getString("largeImageUrl")
        if (__largeImageUrl == null) {
          throw IllegalArgumentException("Argument \"largeImageUrl\" is marked as non-null but was passed a null value.")
        }
      } else {
        __largeImageUrl = ""
      }
      return LargePictureDetailsFragmentArgs(__largeImageUrl)
    }
  }
}
