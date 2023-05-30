from java.util import Random
r = Random()
sum = 0
for i in xrange(cnt):
	randomNum = r.nextInt()
	print randomNum
	sum += randomNum