package ir.mamhesam.poemskurd.feature.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import ir.mamhesam.poemskurd.R
import ir.mamhesam.poemskurd.base.BaseFragment
import ir.mamhesam.poemskurd.data.ResponseBanners
import ir.mamhesam.poemskurd.feature.home.adapter.BannersAdapter
import ir.mamhesam.poemskurd.feature.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : BaseFragment() {

    val homeViewModel: HomeViewModel by viewModel()
    val handler = Handler(Looper.myLooper()!!)
    var bannersSlider: List<ResponseBanners>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.bannersLiveData.observe(viewLifecycleOwner) {
            bannersSlider = it

            val bannersAdapter: BannersAdapter by inject { parametersOf(it) }
            viewPager_banners.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            viewPager_banners.clipToPadding = false
            viewPager_banners.clipChildren = false
            viewPager_banners.offscreenPageLimit = 3
            viewPager_banners.getChildAt(0)!!.overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(20))
            transformer.addTransformer { page, position ->
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.1f
            }

            viewPager_banners.setPageTransformer(transformer)

            viewPager_banners.adapter = bannersAdapter
            dots_indicator.setViewPager2(viewPager_banners)

            viewPager_banners.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    handler.removeCallbacks(sliderRunnable)
                    handler.postDelayed(sliderRunnable, 5000)
                }
            })

        }
        homeViewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            setProgressBar(it)
        }


    }
    private val sliderRunnable = Runnable {

        if (this == null) return@Runnable
        val index: Int = viewPager_banners.currentItem + 1
        viewPager_banners.currentItem = index
        if (index > bannersSlider!!.size - 1)
            viewPager_banners.currentItem = 0
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(sliderRunnable, 5000)
    }


}