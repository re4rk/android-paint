package woowacourse.paint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import woowacourse.paint.R
import woowacourse.paint.databinding.ActivityMainBinding
import woowacourse.paint.model.BrushCircle
import woowacourse.paint.model.BrushEraser
import woowacourse.paint.model.BrushPen
import woowacourse.paint.model.BrushRect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setUpView()
    }

    private fun setUpBinding() {
        setContentView(binding.root)
    }

    private fun setUpView() = with(binding) {
        binding.rvColors.adapter = ColorAdapter { binding.paintingPaper.brushColor = it }

        rsSlider.addOnChangeListener { _, value, _ -> paintingPaper.brushSize = value }

        paintingPaper.onUndoHistoryChangeListener = { btnUndo.isEnabled = it }

        paintingPaper.onRedoHistoryChangeListener = { btnRedo.isEnabled = it }

        rgShapes.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbPen -> paintingPaper.brushGenerator = ::BrushPen
                R.id.rbRect -> paintingPaper.brushGenerator = ::BrushRect
                R.id.rbCircle -> paintingPaper.brushGenerator = ::BrushCircle
                R.id.rbEraser -> paintingPaper.brushGenerator = ::BrushEraser
            }
        }
    }
}
