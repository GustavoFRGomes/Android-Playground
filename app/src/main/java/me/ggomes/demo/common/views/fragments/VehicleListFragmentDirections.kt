package me.ggomes.demo.common.views.fragments

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String
import me.ggomes.demo.R

public class VehicleListFragmentDirections private constructor() {
  private data class ActionVehicleListFragmentToLargePictureDetailsFragment(
    public val largeImageUrl: String = ""
  ) : NavDirections {
    public override fun getActionId(): Int =
        R.id.action_vehicleListFragment_to_largePictureDetailsFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("largeImageUrl", this.largeImageUrl)
      return result
    }
  }

  public companion object {
    public fun actionVehicleListFragmentToLargePictureDetailsFragment(largeImageUrl: String = ""):
        NavDirections = ActionVehicleListFragmentToLargePictureDetailsFragment(largeImageUrl)
  }
}
