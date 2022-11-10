package com.yunusbedir.widget.spinner

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo.IME_FLAG_NO_FULLSCREEN
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.yunusbedir.widget.R

class CustomSpinner : TextInputLayout, AdapterView.OnItemClickListener {

    private var attrs: AttributeSet? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(
        context, attrs
    ) {
        this.attrs = attrs
        getAttributes()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context, attrs, defStyle
    ) {
        this.attrs = attrs
        getAttributes()
    }

    var currentList: List<String>
        set(value) {
            autoCompleteTextViewAdapter.setList(value)
        }
        get() = autoCompleteTextViewAdapter.getList()

    private var autoCompleteTextViewAdapter = CustomArrayAdapter(
        context, R.layout.custom_item_view_spinner, arrayListOf()
    )

    private var autoCompleteTextView: AutoCompleteTextView? = null
    private var onItemSelectedListener: OnItemSelectedListener? = null
    private var onTextChangeListener: OnTextChangeListener? = null

    private var isSpinner = true
        set(value) {
            autoCompleteTextView?.apply {
                this.isFocusable = value.not()
            }
            field = value
        }

    init {
        boxBackgroundMode = BOX_BACKGROUND_OUTLINE
        boxBackgroundColor = Color.WHITE
        endIconMode = END_ICON_DROPDOWN_MENU
        setPadding(ZERO, ZERO, ZERO, ZERO)
        setBoxCornerRadii(BOX_CORNER_RADII, BOX_CORNER_RADII, BOX_CORNER_RADII, BOX_CORNER_RADII)
        createAutoCompleteTextView()
        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) autoCompleteTextView?.showDropDown()
        }
    }

    private fun getAttributes() {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.CustomSpinner, 0, 0
        )
        isSpinner = a.getBoolean(R.styleable.CustomSpinner_isSpinner, true)
        a.recycle()
    }

    private fun createAutoCompleteTextView() {
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        autoCompleteTextView = AutoCompleteTextView(context)
        autoCompleteTextView?.setPadding(PADDING_LEFT, ZERO, ZERO, ZERO)
        autoCompleteTextView?.layoutParams = layoutParams
        autoCompleteTextView?.setAdapter(autoCompleteTextViewAdapter)
        autoCompleteTextView?.imeOptions = IME_FLAG_NO_FULLSCREEN
        autoCompleteTextView?.threshold = 1
        autoCompleteTextView?.isSingleLine = true
        autoCompleteTextView?.maxLines = 1
        autoCompleteTextView?.textSize = TEXT_SIZE
        autoCompleteTextView?.onItemClickListener = this
        autoCompleteTextView?.doOnTextChanged { text, _, _, _ ->
            onTextChangeListener?.onTextChange(text)
        }
        autoCompleteTextView?.setOnClickListener {
            autoCompleteTextView?.showDropDown()
        }
        autoCompleteTextView?.setSelection(0)
        addView(autoCompleteTextView)
    }

    fun getSelectedItem(): String {
        return autoCompleteTextView?.text.toString()
    }

    fun getSelectedItemPosition(): Int {
        return autoCompleteTextView?.selectionStart ?: 0
    }

    /**
     * setSelectionItem -1 when you want select last item
     */
    fun setSelectionItem(position: Int) {
        if (position == -1 && currentList.isNotEmpty()) {
            autoCompleteTextView?.setSelection(currentList.size - 1)
            return
        }
        if (position < currentList.size && position >= 0 && currentList.isNotEmpty()) {
            if (position < currentList.size) {
                autoCompleteTextView?.setText(currentList[position], false)
                onItemSelectedListener?.onItemSelected(this, position)
            }
        }
    }

    fun textClear() {
        autoCompleteTextView?.setText("", false)
    }

    fun setText(text: String) {
        autoCompleteTextView?.setText(text, false)
    }

    fun setOnItemSelectedListener(onItemSelectedListener: OnItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    fun setOnTextChangeListener(onTextChangeListener: OnTextChangeListener) {
        this.onTextChangeListener = onTextChangeListener
    }

    //region Override Methods
    //region override onItemSelectedListener
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onItemSelectedListener?.onItemSelected(this, position)
    }
    //endregion override onItemSelectedListener
    //endregion Override Methods

    companion object {
        const val TEXT_SIZE = 10F
        const val BOX_CORNER_RADII = 10F
        const val PADDING_LEFT = 10
        const val ZERO = 0
    }
}
