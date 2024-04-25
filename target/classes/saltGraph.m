
function retval = saltGraph (input1, input2)
retval = input1
for i = 2:length(input1)
  rng = input2 * rand(1)
  coinflip = rand(1)
  if(coinflip > .5)
    retval(i,2) = retval(i,2) + rng
   else
    retval(i,2) = retval(i,2) - rng
  end
end
endfunction
