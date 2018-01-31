def OutputError ( output, target ):
	error = output(1-output)(target-output)
	return error

def ChangeOutputWeight (output, weight, error, n):
# As far as my understanding is concerned, the 'n' variable is the learning rate
	newWeight = weight + n(error)(output)
	return newWeight

def HiddenLayerError ( output, weights, errorList ):
	C1 = output(1-output)								# Contains first part of calcaulation
	C2 = 0												# Initializes second part of calculation
	for x in range(0, len(errorList)):					# Calculates the second component 
		C2 = C2 + weights[x]*errorList[x]
	error = C1*C2										# Merges the components
	return error
	
def ChangeHiddenWeights ( weight, inpt, error, n ):
		newWeight = weight + n(error)(inpt)
		return newWeight
	



		
	
