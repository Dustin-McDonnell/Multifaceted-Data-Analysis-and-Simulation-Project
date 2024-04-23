
function retval = plotGraph(input1)
  for i = 2:length(input1)
    plot(input1(i,1), input1(i,2))
    hold on
  end
endfunction
