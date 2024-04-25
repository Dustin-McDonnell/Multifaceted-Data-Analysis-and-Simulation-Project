
function retval = smoothGraph (input1, input2)
retval = input1
for i = 2:length(retval)
  value = 0
  counter = 0
  for k = -input2:input2
    if(i + k <= length(retval) && i + k >=2)
      value = retval(i+k,2)
      counter = counter + 1
     end
  endfor
  retval(i,2) = value/counter
  endfor
endfunction
