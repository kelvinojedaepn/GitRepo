let screenDisplay = document.querySelector('.screen')
const buttons = document.querySelectorAll('button')
let calculation = []
let accumulativeCalculation

buttons.forEach(button => button.addEventListener('click', () => calculate(button)))

function calculate(button) {
    const value = button.textContent
    if (value === "CLEAR") {
        calculation = []
        screenDisplay.textContent = "."
    } else if (value === "=") {
        screenDisplay.textContent = eval(accumulativeCalculation)
    } else {
        calculation.push(value)
        console.log(value)
        console.log(calculation)
        accumulativeCalculation = calculation.join('')
        screenDisplay.textContent = accumulativeCalculation
    }

}