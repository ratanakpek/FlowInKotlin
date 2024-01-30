package ratanak.pek.flowinkotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val countDownFlow = flow<Int> {
        val startingValue = 5
        var currentValue = startingValue
        while (currentValue > 0) {
            delay(1000)
            currentValue--
            println(" loop $currentValue")
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {
//        countDownFlow.onEach {
//            println("count down $it")
//        }.launchIn()

        viewModelScope.launch {
           val count =  countDownFlow
               .reduce { accumulator, value ->
                   value + accumulator
               }
            println("The current time is $count")
        }
    }
}