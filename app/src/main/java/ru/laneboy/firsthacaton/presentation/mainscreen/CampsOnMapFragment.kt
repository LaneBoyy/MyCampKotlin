package ru.laneboy.firsthacaton.presentation.mainscreen

import android.content.Context
import android.graphics.PointF
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.laneboy.firsthacaton.R
import ru.laneboy.firsthacaton.databinding.FragmentCampsOnMapBinding


class CampsOnMapFragment : Fragment(), UserLocationObjectListener {

    private var _binding: FragmentCampsOnMapBinding? = null
    private val binding: FragmentCampsOnMapBinding
        get() = _binding?: throw RuntimeException("FragmentCampsOnMapBinding = null")

    private var cameraLatitude = 0.0
    private var cameraLongitude = 0.0

    private var initialized = false

    private lateinit var mapObjects: MapObjectCollection
    private lateinit var userLocationLayer: UserLocationLayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initialize(MAPKIT_API_KEY, requireActivity())
        _binding = FragmentCampsOnMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMap()
        setButtonListener()
    }

    private fun setMap() {
        binding.mapview.map.isRotateGesturesEnabled = true
        val mapKit = MapKitFactory.getInstance()
        mapKit.resetLocationManagerToDefault()
        userLocationLayer = mapKit.createUserLocationLayer(binding.mapview.mapWindow)
        userLocationLayer.isVisible = true
        userLocationLayer.isHeadingEnabled = false
        userLocationLayer.setObjectListener(this)
        mapObjects = binding.mapview.map.mapObjects.addCollection()
    }

    private fun initialize(apiKey: String, context: Context) {
        if (initialized) {
            return
        }

        MapKitFactory.setApiKey(apiKey)
        MapKitFactory.initialize(context)
        initialized = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    override fun onStop() {
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }


    override fun onPause() {
        super.onPause()
        MapKitFactory.getInstance().onStop()
        binding.mapview.onStop()
    }

    override fun onObjectAdded(userLocationView: UserLocationView) {
        userLocationView.arrow.setIcon(
            ImageProvider.fromResource(
                requireActivity(), R.drawable.search_result
            )
        )
        val pinIcon = userLocationView.pin.useCompositeIcon()
        pinIcon.setIcon(
            "pin",
            ImageProvider.fromResource(requireActivity(), R.drawable.search_result),
            IconStyle().setAnchor(PointF(0.5f, 0.5f))
                .setRotationType(RotationType.ROTATE)
                .setZIndex(1f)
                .setScale(0.5f)
        )
        setRoute(userLocationView)
    }

    private fun setRoute(userLocationView: UserLocationView) {

        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                try {
                    val newLatitude = userLocationView.arrow.geometry.latitude
                    val newLongitude = userLocationView.arrow.geometry.longitude
                    if (!newLatitude.equals(0.0) || !newLongitude.equals(0.0)) {
                        cameraLatitude = newLatitude
                        cameraLongitude = newLongitude
                    }
                } catch (_: Exception){

                }

                delay(3000)
            }
        }
    }

    private fun setButtonListener() {
        binding.fbFindUserLocation.setOnClickListener {
            val point = Point(cameraLatitude, cameraLongitude)
            if (!point.latitude.equals(0.0) && !point.longitude.equals(0.0)) {
                binding.mapview.map.move(
                    CameraPosition(point, 14F, 0F, 0F)
                )
            }
        }
    }

    override fun onObjectRemoved(userLocationView: UserLocationView) {
    }

    override fun onObjectUpdated(userLocationView: UserLocationView, p1: ObjectEvent) {
    }

    companion object {
        fun newInstance() = CampsOnMapFragment()

        const val PERMISSIONS_REQUEST_FINE_LOCATION = 1
        const val MAPKIT_API_KEY = "0f59f418-349a-4b78-ba49-04ebddce4f5b"
    }
}